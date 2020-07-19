package xyz.yhhu.blog.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tiger
 * @date 2020-07-18
 * @see xyz.yhhu.blog.entity.vo
 **/
@Data
public class AdminVO implements Serializable {
    private String nickname;
    private String profile;
    private String avatar;
    private String email;
}
