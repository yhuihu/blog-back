package com.blog.blogBack.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
@Data
public class Comment extends BaseEntity implements Serializable {
    /**
     * 对应的博客
     */
    @Column(name = "blog_id")
    private Integer blogId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论日期
     */
    @Column(name = "comment_date")
    private Date commentDate;

    /**
     * 读者/评论者
     */
    @Column(name = "reader_id")
    private Integer readerId;

    /**
     * 回复的评论，空表示评论博客
     */
    @Column(name = "reply_comment_id")
    private Integer replyCommentId;
}
