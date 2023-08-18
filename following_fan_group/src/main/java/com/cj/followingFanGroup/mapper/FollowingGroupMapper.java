package com.cj.followingFanGroup.mapper;

import com.cj.followingFanGroup.entity.FollowingUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowingGroupMapper {

    /**
     * 根据用户id, 获取所有分组
     *
     * @param userId
     * @return
     */
    List<FollowingUser> getFollowingGroupByUserId(Long userId);
}
