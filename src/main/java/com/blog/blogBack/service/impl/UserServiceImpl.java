package com.blog.blogBack.service.impl;

import com.blog.blogBack.dao.UserMapper;
import com.blog.blogBack.entity.User;
import com.blog.blogBack.framework.service.AbstractService;
import com.blog.blogBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.blog.blogBack.util.Encryption.verify;

/**
 * @description:
 * @author: Tiger
 * @create: 2019-07-29
 **/
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User Login(String username, String password) {
        User user = new User();
        user = userMapper.GetDataBasePassword(username);
        if (user != null) {
            if (!verify(password, user.getPassword())) {
                user = null;
            }
        }
        return user;
    }

    @Override
    public User hasUserName(String username) {
        return userMapper.hasUserName(username);
    }

    @Override
    public User hasUserEmail(String email) {
        return userMapper.hasUserEmail(email);
    }

    @Override
    public Integer registerUser(User user) {
        return userMapper.insert(user);
    }
}