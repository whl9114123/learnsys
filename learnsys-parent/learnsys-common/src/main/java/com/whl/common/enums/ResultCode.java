package com.whl.common.enums;

public enum ResultCode {

    /**
     * 系统参数
     */
    SUCCESS(0, "请求成功"),
    FAILURE(1, "请求失败"),
    PARAM_ERROR(400, "参数错误"),
    NO_HANDLER_FOUND(404, "url错误，请检查"),
    EXCEPTION(500, "服务内部出现异常"),
    INVAILDTOKEN(1000, "用户token认证失败，请重新登陆"),

    /**
     * 自定义参数
     */
    UPDATE_NONE(2000, "更新了0条记录"),
    RECORD_UNFIND(2001, "未查寻到相关记录"),
    LOCKED(2002, "有用户正在操作，请稍候再试"),
    REPEAT_LIKED(2003, "重复点赞"),
    CUSTOM_EXCEPTION(2019, "自定义异常"),
    /**
     * 视频付费自定义参数
     */
    CREDITS_CHANGED(2020, "视频所需积分发生变化"),
    CREDITS_LACK(2021, "积分不足");

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
