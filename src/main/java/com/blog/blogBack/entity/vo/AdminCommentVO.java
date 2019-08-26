package com.blog.blogBack.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminCommentVO extends CommentVO implements Serializable {

    private Integer id;
    private String ip;
    private String blogTitle;
}
