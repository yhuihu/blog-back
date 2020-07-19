package xyz.yhhu.blog.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tiger
 * @date 2020-07-15
 * @see xyz.yhhu.blog.entity.vo
 **/
@Data
public class BlogVO implements Serializable {
    private Integer id;
    private String title;
    private String summary;
    private Date createDate;
    private Date updateDate;
    private Integer clickCount;
    private Integer commentCount;
    private Integer likeCount;
    private String tag;
    private String blogImage;
    private String originalUrl;
    private String content;
    private String typeName;
}
