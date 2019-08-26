package com.blog.blogBack.entity;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
public class Blog extends BaseEntity implements Serializable {
    /**
     * 标题
     */
    private String title;

    /**
     * 概览
     */
    private String summary;

    /**
     * 创建日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新日期
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 点击数
     */
    @Column(name = "click_count")
    private Integer clickCount;

    /**
     * 评论数
     */
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     * 喜欢数
     */
    @Column(name = "like_count")
    private Integer likeCount;

    /**
     * 博客标签(如1,2,3表示具有三种标签)
     */
    private String tag;

    /**
     * 博客图片
     */
    @Column(name = "blog_image")
    private String blogImage;

    /**
     * 原文地址（如果非原创）
     */
    @Column(name = "original_url")
    private String originalUrl;

    /**
     * 0原创/1转载/2翻译
     */
    private Integer type;
}
