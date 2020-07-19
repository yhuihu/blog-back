package xyz.yhhu.blog.framework;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Tiger
 * @date 2020-07-15
 * @see xyz.yhhu.blog.framework
 **/
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE).setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result().setCode(ResultCode.FAIL).setMessage(message);
    }

    /**
     * 将异常转化成字符串
     * @param e
     * @return
     */
    public static String exceptionToString(Exception e) {
        if (e == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

}
