package com.cj.followingFanGroup.service;

import com.cj.followingFanGroup.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> getAllUser();

    /**
     * 根据用户id, 获取用户信息
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 根据idList, 获取所有的用户
     *
     * @param idList
     * @return
     */
    List<User> getUserByIdList(List<Long> idList);
}
