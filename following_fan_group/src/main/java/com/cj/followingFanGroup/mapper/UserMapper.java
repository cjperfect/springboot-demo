package com.cj.followingFanGroup.mapper;

import com.cj.followingFanGroup.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> getAllUser();

    /**
     * 根据用户id获取用户信息
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
    List<User> getUserByIdList(@Param("idList") List<Long> idList);
}
