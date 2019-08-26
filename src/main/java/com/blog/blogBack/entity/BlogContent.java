package com.blog.blogBack.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "blog_content")
public class BlogContent extends BaseEntity implements Serializable {
    /**
     * 正文
     */
    private String content;
}
