package xyz.yhhu.blog.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Tiger
 * @date 2020-07-21
 * @see xyz.yhhu.blog.entity.vo
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminCommentVO extends CommentVO implements Serializable {

    private Integer id;
    private String ip;
    private String blogTitle;
}
