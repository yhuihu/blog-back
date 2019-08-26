package com.blog.blogBack.framework;

/**
 * @program: blog-back
 * @description: 响应代码枚举
 * @author: Tiger
 * @create: 2019-07-28 22:08
 **/
public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500),//服务器内部错误
    TOKEN_ERROR(50008),
    REDIRECT(2000);
    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}