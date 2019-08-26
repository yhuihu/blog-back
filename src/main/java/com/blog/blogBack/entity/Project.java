package com.blog.blogBack.entity;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class Project extends BaseEntity implements Serializable {
    /**
     * 名称
     */
    private String name;

    /**
     * 项目地址
     */
    private String url;

    /**
     * 项目图片
     */
    private String image;

    /**
     * 项目顺序
     */
    @Column(name = "project_order")
    private Integer projectOrder;
}
