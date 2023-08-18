package com.cj.followingFanGroup.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FollowingGroup {
    // id
    private Long id;

    // 用户id
    private Long userId;

    // 分组code
    private String groupCode;

    // 分组名称
    private String groupName;

    // 创建时间
    private Date createTime;
}
