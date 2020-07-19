package xyz.yhhu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "`comment`")
public class Comment implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 对应的博客
     */
    @TableField(value = "blog_id")
    private Integer blogId;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 评论日期
     */
    @TableField(value = "comment_date")
    private Date commentDate;

    /**
     * 读者/评论者
     */
    @TableField(value = "reader_id")
    private Integer readerId;

    /**
     * 回复的评论，空表示评论博客
     */
    @TableField(value = "reply_comment_id")
    private Integer replyCommentId;
}
