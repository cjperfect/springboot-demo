package com.cj.followingFanGroup.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    // id
    private Long id;

    // 用户昵称
    private String nickName;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date createTime;


    // 关注列表
    private List<User> followingList;
}
