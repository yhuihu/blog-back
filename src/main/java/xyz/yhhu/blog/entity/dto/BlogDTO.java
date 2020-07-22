package xyz.yhhu.blog.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Tiger
 * @date 2020-07-20
 * @see xyz.yhhu.blog.entity.dto
 **/
@Data
public class BlogDTO {
    private Integer id;
    @NotEmpty(message = "博客标题不能为空！")
    private String title;
    @NotEmpty(message = "博客内容不能为空！")
    private String content;
    @NotEmpty(message = "博客标签不能为空！")
    private String tag;
    @NotNull(message = "博客类型不能为空！")
    private Integer type;
    private String originalUrl;
    private String blogImage;
}
