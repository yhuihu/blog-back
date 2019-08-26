package com.blog.blogBack.dao;

import com.blog.blogBack.entity.User;
import com.blog.blogBack.framework.mapper.IMyMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: blog-back
 * @description:
 * @author: Tiger
 * @create: 2019-07-28 23:31
 **/

public interface UserMapper extends IMyMapper<User> {
    @Select("Select * from user u where u.username = #{username}" +
            " and u.password = #{password}")
    User Login(String username, String password);

    @Select("Select * from user u where u.username = #{username} limit 0,1")
    User GetDataBasePassword(String username);

    @Select("Select * from user u where u.username = #{username} limit 1")
    User hasUserName(String username);

    @Select("Select * from user u where u.email = #{email} limit 1")
    User hasUserEmail(String email);

}
