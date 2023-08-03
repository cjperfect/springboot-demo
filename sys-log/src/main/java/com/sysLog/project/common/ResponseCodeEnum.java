package com.sysLog.project.common;

public enum ResponseCodeEnum {
    RC200("200", "请求成功"),
    RC500("500", "系统异常, 请稍后重试"),
    RC404("403", "无权限访问");


    private final String code;

    private final String msg;

    ResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
