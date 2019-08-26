package com.blog.blogBack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户实体类
 * @author: Tiger
 * @create: 2019-07-28
 **/
@Data
public class User extends BaseEntity implements Serializable {
    /**
     * 名字
     */
    private String nickname;

    /**
     * 简介
     */
    private String profile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

//    //单例
//    private static class SingletonUserInstance {
//        private static final User INSTANCE = new User();
//    }
//
//    public static User getSingleUser() {
//        return SingletonUserInstance.INSTANCE;
//    }
}
