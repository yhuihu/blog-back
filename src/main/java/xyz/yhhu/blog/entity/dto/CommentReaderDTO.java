package xyz.yhhu.blog.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Tiger
 * @date 2020-07-18
 * @see xyz.yhhu.blog.entity.dto
 **/
@Data
public class CommentReaderDTO {
    @NotNull(message = "blogId不能为空")
    private Integer blogId;
    private Integer id;
    @NotEmpty(message = "评论内容不能为空")
    private String content;
    @NotEmpty(message = "评论者名称不能为空")
    private String name;
    private String email;
    private String avatar;
    private Integer replyCommentId;
    private Integer inform;
}
