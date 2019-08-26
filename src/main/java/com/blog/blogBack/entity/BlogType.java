package com.blog.blogBack.entity;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "type_name")
@Data
public class BlogType extends BaseEntity implements Serializable {

    /**
     * 名称
     */
    private String name;
}
