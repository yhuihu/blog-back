package xyz.yhhu.blog.framework;

/**
 * @author Tiger
 * @date 2020-07-15
 * @see xyz.yhhu.blog.framework
 **/
public enum ResultCode {
    //成功
    SUCCESS(200),
    //失败
    FAIL(400),
    //未认证（签名错误）
    UNAUTHORIZED(401),
    //接口不存在
    NOT_FOUND(404),
    //服务器内部错误
    INTERNAL_SERVER_ERROR(500),
    //token错误
    TOKEN_ERROR(50008),
    //发送消息
    SEND_MSG(100),
    //收到消息
    GET_MSG(101),
    //页面跳转
    REDIRECT(2000);
    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
