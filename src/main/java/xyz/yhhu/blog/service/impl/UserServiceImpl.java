package xyz.yhhu.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yhhu.blog.entity.User;
import xyz.yhhu.blog.mapper.UserMapper;
import xyz.yhhu.blog.service.UserService;
import xyz.yhhu.blog.utils.Encryption;

/**
 * @author admin
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            if (!Encryption.verify(password, user.getPassword())) {
                user = null;
            }
        }
        return user;
    }
}
