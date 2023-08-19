package com.cj.followingFanGroup.mapper;

import com.cj.followingFanGroup.entity.FollowingUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FollowingUserMapper {


    /**
     * 根据用户id获取所有关注的对象
     *
     * @return
     */
    List<FollowingUser> getFollowingUserByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id 获取粉丝的userId
     *
     * @param userId
     * @return
     */
    List<FollowingUser> getFanByFollowingId(Long userId);
}
