package xyz.yhhu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "blog_content")
public class BlogContent implements Serializable {
    /**
     * 主键，和对应博客一致
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 正文
     */
    @TableField(value = "content")
    private String content;
}
