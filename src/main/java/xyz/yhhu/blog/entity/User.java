package xyz.yhhu.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "`user`")
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名字
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 简介
     */
    @TableField(value = "profile")
    private String profile;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;
}
