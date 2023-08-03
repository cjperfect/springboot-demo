package com.sysLog.project.controller;

import com.sysLog.project.annotation.SysLogAnnotation;
import com.sysLog.project.common.BaseResponse;
import com.sysLog.project.common.LogTypeEnum;
import com.sysLog.project.common.ResponseCodeEnum;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class indexController {

    @GetMapping("/get")
    @ResponseBody
    @SysLogAnnotation(logType = LogTypeEnum.SELECT) // 说明是查询操作
    public BaseResponse<String> getTest() throws InterruptedException {
        Thread.sleep(2000);
        return BaseResponse.success("请求成功");
//        return BaseResponse.fail(ResponseCodeEnum.RC500.getCode(), ResponseCodeEnum.RC500.getMsg());

    }

    @GetMapping("/get-error")
    @ResponseBody
    @SysLogAnnotation(logType = LogTypeEnum.SELECT) // 说明是查询操作
    public BaseResponse<String> getErrorTest() {
        return BaseResponse.fail(ResponseCodeEnum.RC500.getCode(), ResponseCodeEnum.RC500.getMsg());
    }

    @PostMapping("/post")
    @ResponseBody
    @SysLogAnnotation(logType = LogTypeEnum.ADD) // 新增操作
    public BaseResponse<String> postTest() throws InterruptedException {
        Thread.sleep(1000);
        return BaseResponse.success("请求成功");

    }
}
