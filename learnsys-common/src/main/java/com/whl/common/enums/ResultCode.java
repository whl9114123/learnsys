package com.whl.common.enums;

public enum ResultCode {

    /**
     * 系统参数
     */
    SUCCESS(0, "请求成功"),
    FAILURE(1, "请求失败"),
    UNAUTHENTICATED(10002, "您还未登录"),
    UNAUTHORISE(10003, "权限不足"),
    EXCEPTION(500, "服务内部出现异常"),;



    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
