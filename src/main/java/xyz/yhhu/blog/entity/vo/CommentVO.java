package xyz.yhhu.blog.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Tiger
 * @date 2020-07-18
 * @see xyz.yhhu.blog.entity.vo
 **/
@Data
public class CommentVO {
    private Integer id;
    private Integer blogId;
    private Date commentDate;
    private String readerName;
    private String readerAvatar;
    private String content;
    private int readerId;
    private Date receiverDate;
    private String receiverName;
    private String receiverAvatar;
    private String receiverContent;
}
