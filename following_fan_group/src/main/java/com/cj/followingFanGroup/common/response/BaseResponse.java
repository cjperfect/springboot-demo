package com.cj.followingFanGroup.common.response;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private int code;

    private String msg;

    private T data;

    private long timestamp;


    public BaseResponse() {
        // 构造函数
        this.timestamp = System.currentTimeMillis();
    }


    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(ResponseCode.RC200.getCode());
        baseResponse.setMsg(ResponseCode.RC200.getMsg());
        baseResponse.setData(data);
        return baseResponse;
    }


    public static <T> BaseResponse<T> fail(int code, String msg) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        return baseResponse;
    }
}
