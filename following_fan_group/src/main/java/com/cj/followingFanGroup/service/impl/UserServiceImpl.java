package com.cj.followingFanGroup.service.impl;

import com.cj.followingFanGroup.entity.User;
import com.cj.followingFanGroup.mapper.UserMapper;
import com.cj.followingFanGroup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getUserByIdList(List<Long> idList) {
        return userMapper.getUserByIdList(idList);

    }


}
