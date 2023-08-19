package com.cj.followingFanGroup.mapper;

import com.cj.followingFanGroup.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface UserMapper {

    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> getAllUser();

    /**
     * 根据id获取用户
     *
     * @return
     */
    User getUserById(@Param("id") Long id);


    /**
     * 根据idList获取所有用户
     *
     * @return
     */
    List<User> getUserByIdList(@Param("idList") Set<Long> idList);
}
