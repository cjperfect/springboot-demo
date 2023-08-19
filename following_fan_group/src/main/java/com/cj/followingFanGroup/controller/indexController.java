package com.cj.followingFanGroup.controller;

import com.cj.followingFanGroup.common.response.BaseResponse;
import com.cj.followingFanGroup.entity.FollowingGroup;
import com.cj.followingFanGroup.entity.User;
import com.cj.followingFanGroup.service.FollowingUserService;
import com.cj.followingFanGroup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class indexController {

    @Autowired
    private UserService userService;


    @Autowired
    private FollowingUserService followingUserService;

    /**
     * 获取所有的用户
     *
     * @return
     */
    @GetMapping("/getAllUser")
    public BaseResponse<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return BaseResponse.success(allUser);
    }

    /**
     * 获取关注列表
     * 获取用户的关注分组, 及分组里面包含哪些关注人员
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUserFollowing/{userId}")
    public BaseResponse<List<FollowingGroup>> getFollowingUserByUserId(@PathVariable Long userId) {
        List<FollowingGroup> followingGroups = followingUserService.getFollowingUserByUserId(userId);
        return BaseResponse.success(followingGroups);
    }

    /**
     * 获取粉丝列表
     * 获取用户的粉丝, 也就是谁关注了我
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUserFan/{userId}")
    public BaseResponse<List<User>> getUserFan(@PathVariable Long userId) {
        List<User> followingGroups = followingUserService.getUserFan(userId);
        return BaseResponse.success(followingGroups);
    }
}
