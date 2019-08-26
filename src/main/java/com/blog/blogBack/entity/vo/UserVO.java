package com.blog.blogBack.entity.vo;

import com.blog.blogBack.entity.BaseEntity;
import com.blog.blogBack.entity.Reader;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Tiger
 * @create: 2019-07-31
 **/
@Data
public class UserVO extends BaseEntity implements Serializable {
    /**
     * 名字
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    public UserVO transToUserVo(Reader reader){
        UserVO userVo=new UserVO();
        userVo.setId(reader.getId());
        userVo.setAvatar(reader.getAvatar());
        userVo.setEmail(reader.getEmail());
        userVo.setName(reader.getName());
        userVo.setId(reader.getId());
        return userVo;
    }
}
