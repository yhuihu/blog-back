package com.blog.blogBack.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminVO implements Serializable {

    private String nickname;
    private String profile;
    private String avatar;
    private String email;
}
