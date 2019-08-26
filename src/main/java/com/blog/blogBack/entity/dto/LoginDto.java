package com.blog.blogBack.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @description:
 * @author: Tiger
 * @create: 2019-07-30
 **/
@Data
public class LoginDto {
    @NotEmpty(message = "用户名不能为空")
    String username;
    @NotEmpty(message = "密码不能为空")
    String password;
}
