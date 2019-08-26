package com.blog.blogBack.dao;

import com.blog.blogBack.entity.Reader;
import com.blog.blogBack.framework.mapper.IMyMapper;
import org.apache.ibatis.annotations.Select;

public interface ReaderMapper extends IMyMapper<Reader> {
    @Select("Select * from reader u where u.username = #{username}" +
            " and u.password = #{password}")
    Reader Login(String username, String password);

    @Select("Select * from reader u where u.username = #{username} limit 0,1")
    Reader GetDataBasePassword(String username);

    @Select("Select * from reader u where u.username = #{username} limit 1")
    Reader hasUserName(String username);

    @Select("Select * from reader u where u.email = #{email} limit 1")
    Reader hasUserEmail(String email);
}