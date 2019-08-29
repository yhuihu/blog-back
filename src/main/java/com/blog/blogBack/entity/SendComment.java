package com.blog.blogBack.entity;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class SendComment extends BaseEntity implements Serializable {
    private String email;
    private String content;
    private String title;
    @Column(name = "comment_id")
    private Integer commentId;
    @Column(name = "blog_id")
    private Integer blogId;
    @Column(name = "reader_name")
    private String readerName;
}
