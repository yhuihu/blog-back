package xyz.yhhu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@TableName(value = "project")
public class Project implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField(value = "name")
    @NotEmpty(message = "项目名称不能为空！")
    private String name;

    /**
     * 项目地址
     */
    @TableField(value = "url")
    @NotEmpty(message = "项目链接不能为空！")
    private String url;

    /**
     * 项目图片
     */
    @TableField(value = "image")
    private String image;

    /**
     * 项目顺序
     */
    @TableField(value = "project_order")
    @NotNull(message = "项目次序不能为空！")
    private Integer projectOrder;
}
