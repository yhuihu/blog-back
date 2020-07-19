package xyz.yhhu.blog.framework;

/**
 * @author Tiger
 * @date 2020-07-15
 * @see xyz.yhhu.blog.framework
 **/
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
