package com.cj.followingFanGroup.entity;

import lombok.Data;

import java.util.Date;

/**
 * 关注用户的实体类
 */
@Data
public class FollowingUser {
    // id
    private Long id;

    // 用户id
    private Long userId;

    // 被关注的用户id
    private Long FollowingId;

    // 被关注的用户放入哪个分组
    private Long groupId;

    // 创建时间
    private Date createTime;

    // 关注用户的详情
    private User user;

}
