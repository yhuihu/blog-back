package com.blog.blogBack.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
public class Link extends BaseEntity implements Serializable {

    /**
     * 链接名称
     */
    @Column(name = "link_name")
    private String linkName;

    /**
     * 链接地址
     */
    @Column(name = "link_url")
    private String linkUrl;

    /**
     * 链接次序（小的在前）
     */
    @Column(name = "link_order")
    private Integer linkOrder;

}