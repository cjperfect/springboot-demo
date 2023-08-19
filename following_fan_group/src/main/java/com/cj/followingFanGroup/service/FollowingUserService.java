package com.cj.followingFanGroup.service;

import com.cj.followingFanGroup.entity.FollowingGroup;
import com.cj.followingFanGroup.entity.User;

import java.util.List;

public interface FollowingUserService {

    /**
     * 获取用户所有的关注分组(分组里面包含所有关注者)
     *
     * @param userId
     * @return
     */
    List<FollowingGroup> getFollowingUserByUserId(Long userId);

    /**
     * 获取粉丝, 哪些用户关注了我
     *
     * @param userId
     * @return
     */
    List<User> getUserFan(Long userId);
}
