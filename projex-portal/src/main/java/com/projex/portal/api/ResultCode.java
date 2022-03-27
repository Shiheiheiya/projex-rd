package com.projex.portal.api;

/**
 * 枚举了一些常用API操作码
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(2200, "操作成功"),
    FAILED(2500, "操作失败"),
    VALIDATE_FAILED(2400, "参数检验失败"),
    UNAUTHORIZED(1401, "暂未登录或token已经过期"),
    FORBIDDEN(1403, "没有相关权限"),
    ERROR_MSG(1404,"用户名或密码错误");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
