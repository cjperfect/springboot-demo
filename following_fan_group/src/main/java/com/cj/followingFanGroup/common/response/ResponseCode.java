package com.cj.followingFanGroup.common.response;

public enum ResponseCode {
    RC200(200, "请求成功"),
    RC500(500, "系统异常, 请稍后重试"),
    RC404(403, "无权限访问");


    private final int code;

    private final String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
