package xyz.yhhu.blog.framework;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author Tiger
 * @date 2020-07-15
 * @see xyz.yhhu.blog.framework
 **/
public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
