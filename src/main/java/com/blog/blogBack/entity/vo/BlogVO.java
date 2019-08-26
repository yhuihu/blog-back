package com.blog.blogBack.entity.vo;

import com.blog.blogBack.entity.Blog;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BlogVO implements Serializable {
    private Integer id;
    private String title;
    private String summary;
    private Date createDate;
    private Date updateDate;
    private Integer clickCount;
    private Integer commentCount;
    private Integer likeCount;
    private String tag;
    private String blogImage;
    private String originalUrl;
    private String content;
    private String typeName;
}
