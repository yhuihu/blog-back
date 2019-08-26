package com.blog.blogBack.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Data
public class Reader extends BaseEntity implements Serializable {

    /**
     * 账户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 名字
     */
    private String name;

    /*
     * GITHUB_UUID
     * */
    private Integer uuid;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 收到回复时是否接收邮件
     */
    private Integer inform;
}
