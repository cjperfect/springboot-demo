package com.cj.followingFanGroup.service.impl;

import com.cj.followingFanGroup.entity.FollowingUser;
import com.cj.followingFanGroup.entity.User;
import com.cj.followingFanGroup.mapper.FollowingUserMapper;
import com.cj.followingFanGroup.mapper.UserMapper;
import com.cj.followingFanGroup.service.FollowingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowingUserServiceImpl implements FollowingUserService {

    @Autowired
    private FollowingUserMapper followingUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getFollowingUserById(Long userId) {
        // 获取关注用户的id
        List<Long> followingIdList = this.getFollowingIdList(userId);
        // 查询出当前的用户
        User user = userMapper.getUserById(userId);
        // 获取当前用户所有关注的用户信息
        List<User> followingList = userMapper.getUserByIdList(followingIdList);
        // 将关注的用户插入到当前用户的followingList属性中
        user.setFollowingList(followingList);
        return user;
    }

    /**
     * 根据id获取所有关注用户的id
     *
     * @param userId
     * @return
     */
    public List<Long> getFollowingIdList(Long userId) {
        List<FollowingUser> followingUsers = followingUserMapper.getFollowingUserById(userId);
        return followingUsers.stream().map(FollowingUser::getFollowingId).collect(Collectors.toList());
    }

}
