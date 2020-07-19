package xyz.yhhu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "blog")
public class Blog implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 概览
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 创建日期
     */
    @TableField(value = "create_date")
    private Date createDate;

    /**
     * 更新日期
     */
    @TableField(value = "update_date")
    private Date updateDate;

    /**
     * 点击数
     */
    @TableField(value = "click_count")
    private Integer clickCount;

    /**
     * 评论数
     */
    @TableField(value = "comment_count")
    private Integer commentCount;

    /**
     * 喜欢数
     */
    @TableField(value = "like_count")
    private Integer likeCount;

    /**
     * 博客标签(如1,2,3表示具有三种标签)
     */
    @TableField(value = "tag")
    private String tag;

    /**
     * 博客图片
     */
    @TableField(value = "blog_image")
    private String blogImage;

    /**
     * 原文地址（如果非原创）
     */
    @TableField(value = "original_url")
    private String originalUrl;

    /**
     * 0原创/1转载/2翻译
     */
    @TableField(value = "type")
    private Integer type;
}
