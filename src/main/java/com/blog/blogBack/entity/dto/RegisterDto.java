package com.blog.blogBack.entity.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @description:
 * @author: Tiger
 * @create: 2019-08-02
 **/
@Data
public class RegisterDto {
    @NotEmpty(message = "用户名不能为空")
    String username;
    @NotEmpty(message = "密码不能为空")
    String password;
    @NotEmpty(message = "名称不能为空")
    String name;
    @NotEmpty(message = "邮箱不能为空")
    String email;
    @NotEmpty(message = "验证码不能为空")
    String emailCode;
    String avatar;
}
