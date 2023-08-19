package com.cj.followingFanGroup.service;

import com.cj.followingFanGroup.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> getAllUser();


}
