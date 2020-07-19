package xyz.yhhu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "link")
public class Link implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 链接名称
     */
    @TableField(value = "link_name")
    private String linkName;

    /**
     * 链接地址
     */
    @TableField(value = "link_url")
    private String linkUrl;

    /**
     * 链接次序（小的在前）
     */
    @TableField(value = "link_order")
    private Integer linkOrder;
}
