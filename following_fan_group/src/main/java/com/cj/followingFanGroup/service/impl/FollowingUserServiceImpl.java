package com.cj.followingFanGroup.service.impl;

import com.cj.followingFanGroup.entity.FollowingGroup;
import com.cj.followingFanGroup.entity.FollowingUser;
import com.cj.followingFanGroup.entity.User;
import com.cj.followingFanGroup.mapper.FollowingGroupMapper;
import com.cj.followingFanGroup.mapper.FollowingUserMapper;
import com.cj.followingFanGroup.mapper.UserMapper;
import com.cj.followingFanGroup.service.FollowingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FollowingUserServiceImpl implements FollowingUserService {

    @Autowired
    private FollowingUserMapper followingUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowingGroupMapper followingGroupMapper;

    @Override
    public List<FollowingGroup> getFollowingUserByUserId(Long userId) {

        // 判断是否有这个用户
        if (checkUser(userId) == null) {
            throw new RuntimeException("当前用户不存在");
        }

        /**
         * 1. 获取所有分组
         * 2. 获取所有的关注者
         * 3. 将关注者归类到指定分组
         */

        // 1. 获取所有分组
        List<FollowingGroup> allGroupByUserId = followingGroupMapper.getAllGroupByUserId(userId);// 根据用户获取所有的分组

        // 2. 获取所有关注者
        List<FollowingUser> followingUserList = followingUserMapper.getFollowingUserByUserId(userId); // 根据用户id就获取所有关注对象
        Set<Long> followingIdList = followingUserList.stream().map(FollowingUser::getFollowingId).collect(Collectors.toSet()); // 获取所有关注者的userId
        List<User> userList = userMapper.getUserByIdList(followingIdList); // 根据关注者的userId, 获取对应的User信息

        // 往FollowingUser里面添加User信息
        for (FollowingUser followingUser : followingUserList) {
            for (User user : userList) {
                if (followingUser.getFollowingId().equals(user.getId())) {
                    followingUser.setUser(user);
                }
            }
        }

        // 将对应的用户插入到指定分组
        for (FollowingGroup followingGroup : allGroupByUserId) {
            List<User> users = new ArrayList<>();

            for (FollowingUser followingUser : followingUserList) {
                if (followingGroup.getId().equals(followingUser.getGroupId())) {
                    users.add(followingUser.getUser());
                }
            }
            followingGroup.setFollowingList(users);
        }


        return allGroupByUserId;
    }

    @Override
    public List<User> getUserFan(Long userId) {

        // 判断是否有这个用户
        if (checkUser(userId) == null) {
            throw new RuntimeException("当前用户不存在");
        }

        // 获取关注的id(followingId)为自己的id
        List<FollowingUser> followingUsers = followingUserMapper.getFanByFollowingId(userId);

        // 获取粉丝的userId
        Set<Long> fanUserId = followingUsers.stream().map(FollowingUser::getUserId).collect(Collectors.toSet());

        return userMapper.getUserByIdList(fanUserId);
    }


    private User checkUser(Long userId) {

        // 判断是否有这个用户
        return userMapper.getUserById(userId);

    }

}
