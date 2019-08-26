package com.blog.blogBack.entity.dto;

import lombok.Data;

@Data
public class CommentReaderDTO {

    private Integer blogId;
    private Integer id;
    private String content;
    private String name;
    private String email;
    private String avatar;
    private Integer replyCommentId;
    private Integer inform;

}
