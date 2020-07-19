package xyz.yhhu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "carousel")
public class Carousel implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图片
     */
    @TableField(value = "image")
    private String image;

    /**
     * 顺序
     */
    @TableField(value = "image_order")
    private Integer imageOrder;

    /**
     * 链接
     */
    @TableField(value = "url")
    private String url;
}
