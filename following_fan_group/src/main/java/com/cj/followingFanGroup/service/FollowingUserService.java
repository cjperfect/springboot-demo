package com.cj.followingFanGroup.service;

import com.cj.followingFanGroup.entity.User;

import java.util.List;

public interface FollowingUserService {

    /**
     * 根据用户id, 获取所有关注用户
     *
     * @param userId
     * @return
     */
    User getFollowingUserById(Long userId);



}
