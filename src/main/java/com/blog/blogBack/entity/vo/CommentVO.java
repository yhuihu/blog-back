package com.blog.blogBack.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class CommentVO implements Serializable {

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
