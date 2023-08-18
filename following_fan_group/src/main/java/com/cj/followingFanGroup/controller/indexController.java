package com.cj.followingFanGroup.controller;

import com.cj.followingFanGroup.common.response.BaseResponse;
import com.cj.followingFanGroup.entity.User;
import com.cj.followingFanGroup.service.FollowingUserService;
import com.cj.followingFanGroup.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
     * 获取用户关注的列表
     *
     * @return
     */
    @GetMapping("/getFollowingUser/{userId}")
    public BaseResponse<User> getFollowingUser(@PathVariable Long userId) {
        User user = followingUserService.getFollowingUserById(userId);
        return BaseResponse.success(user);
    }


    /**
     * 获取用户的关注分组, 及分组里面包含哪些关注人员
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUserFollowingGroup/{userId}")
    public BaseResponse<User> getUserFollowingGroup(@PathVariable Long userId) {
        return BaseResponse.success(null);
    }
}
