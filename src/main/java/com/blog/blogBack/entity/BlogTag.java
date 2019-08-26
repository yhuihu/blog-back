package com.blog.blogBack.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tag_name")
public class BlogTag extends BaseEntity implements Serializable {
    /**
     * 名称
     */
    private String name;

}
