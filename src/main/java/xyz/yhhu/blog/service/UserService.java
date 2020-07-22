package xyz.yhhu.blog.service;

import xyz.yhhu.blog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * @author admin
 */
@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
public interface UserService extends IService<User>{
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);
}
