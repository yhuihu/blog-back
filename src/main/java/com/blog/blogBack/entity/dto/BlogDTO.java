package com.blog.blogBack.entity.dto;

import lombok.Data;

@Data
public class BlogDTO {

    private Integer id;
    private String title;
    private String content;
    private String tag;
    private Integer type;
    private String originalUrl;
    private String blogImage;

}
