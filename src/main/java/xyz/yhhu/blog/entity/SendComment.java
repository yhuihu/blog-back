package xyz.yhhu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "send_comment")
public class SendComment implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "email")
    private String email;

    @TableField(value = "title")
    private String title;

    @TableField(value = "comment_id")
    private Integer commentId;

    @TableField(value = "blog_id")
    private Integer blogId;

    @TableField(value = "content")
    private String content;

    @TableField(value = "reader_name")
    private String readerName;
}
