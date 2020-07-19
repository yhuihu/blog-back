package xyz.yhhu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "reader")
public class Reader implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录用的账户
     */
    @TableField(value = "username")
    private String username;

    /**
     * 登录用的密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 名字
     */
    @TableField(value = "name")
    private String name;

    /**
     * ip地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 收到回复时是否接收邮件
     */
    @TableField(value = "inform")
    private Integer inform;

    @TableField(value = "uuid")
    private Integer uuid;
}
