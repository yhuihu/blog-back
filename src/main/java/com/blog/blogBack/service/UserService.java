package com.blog.blogBack.service;

import com.blog.blogBack.entity.User;
import com.blog.blogBack.framework.service.IService;

import java.util.List;

/**
 * @program: blog-back
 * @description:
 * @author: Tiger
 * @create: 2019-07-29 17:16
 **/
public interface UserService extends IService<User> {
    User Login(String username, String password);

    User hasUserName(String username);

    User hasUserEmail(String email);

    Integer registerUser(User user);
}
