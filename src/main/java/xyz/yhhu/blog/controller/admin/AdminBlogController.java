package xyz.yhhu.blog.controller.admin;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yhhu.blog.entity.Blog;
import xyz.yhhu.blog.entity.BlogContent;
import xyz.yhhu.blog.entity.dto.BlogDTO;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.BlogContentService;
import xyz.yhhu.blog.service.BlogService;
import xyz.yhhu.blog.service.TagNameService;
import xyz.yhhu.blog.service.TypeNameService;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author Tiger
 * @date 2020-07-20
 * @see xyz.yhhu.blog.controller.admin
 **/
@RestController
@RequestMapping("admin/blog")
public class AdminBlogController {
    private final Integer SUMMARY_MAX_LENGTH = 100;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogContentService blogContentService;

    @Autowired
    private TagNameService tagNameService;

    @Autowired
    private TypeNameService typeNameService;

    @PostMapping()
    public Result add(@Valid BlogDTO blogDTO) {
        if (typeNameService.getById(blogDTO.getType()) == null) {
            return ResultGenerator.genFailResult("不存在id为" + blogDTO.getType() + "的博客类型！");
        }
        // 博客标签验证
        String tags = blogDTO.getTag();
        for (String tag : tags.split(",")) {
            if (tagNameService.getById(Integer.parseInt(tag)) == null) {
                return ResultGenerator.genFailResult("不存在id为" + tag + "的博客标签！");
            }
        }
        // 保存博客信息
        Blog blog = new Blog();
        blog.setTitle(blogDTO.getTitle());
        blog.setType(blogDTO.getType());
        // 保存标签
        blog.setTag(blogDTO.getTag());
        blog.setSummary(getSummary(blogDTO.getContent()));
        // 保存原文地址
        if (StringUtils.isBlank(blogDTO.getOriginalUrl())) {
            blog.setOriginalUrl(null);
        } else {
            blog.setOriginalUrl(blogDTO.getOriginalUrl());
        }
        // 保存图片url
        if (StringUtils.isBlank(blogDTO.getBlogImage())) {
            blog.setBlogImage(null);
        } else {
            blog.setBlogImage(blogDTO.getBlogImage());
        }
        blog.setOriginalUrl(blogDTO.getOriginalUrl());
        blog.setCommentCount(0);
        blog.setLikeCount(0);
        blog.setClickCount(0);
        blog.setCreateDate(new Date());
        blog.setUpdateDate(new Date());
        blogService.save(blog);
        // 保存正文信息
        BlogContent blogContent = new BlogContent();
        blogContent.setId(blog.getId());
        blogContent.setContent(blogDTO.getContent());
        blogContentService.save(blogContent);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping()
    public Result update(@Valid BlogDTO blogDTO) {
        // 参数验证
        Integer blogId = blogDTO.getId();
        if (blogId == null) {
            return ResultGenerator.genFailResult("id不能为空！");
        }
        if (blogService.getById(blogId) == null) {
            return ResultGenerator.genFailResult("不存在id为" + blogId + "的博客！");
        }
        if (StringUtils.isBlank(blogDTO.getBlogImage())) {
            blogDTO.setBlogImage(null);
        }
        Integer blogType = blogDTO.getType();
        if (blogType != null) {
            if (typeNameService.getById(blogType) == null) {
                return ResultGenerator.genFailResult("不存在id为" + blogType + "的博客类型！");
            }
        }
        String tags = blogDTO.getTag();
        for (String tag : tags.split(",")) {
            if (tagNameService.getById(Integer.parseInt(tag)) == null) {
                return ResultGenerator.genFailResult("不存在id为" + tag + "的博客标签！");
            }
        }
        // 更新博客
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDTO, blog);
        blog.setSummary(getSummary(blogDTO.getContent()));
        blog.setUpdateDate(new Date());
        blogService.updateById(blog);
        // 更新博客正文
        if (StringUtils.isNotBlank(blogDTO.getContent())) {
            BlogContent blogContent = new BlogContent();
            blogContent.setId(blogDTO.getId());
            blogContent.setContent(blogDTO.getContent());
            blogContentService.updateById(blogContent);
        }
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping()
    public Result delete(@RequestParam Integer id) {
        // 由于数据库设置的外键策略为CASCADE，会自动删除相应的content
        blogService.removeById(id);
        return ResultGenerator.genSuccessResult();
    }

    private String getSummary(String content) {
        // 不必从全文去找概览
        if (content.length() > 2 * SUMMARY_MAX_LENGTH) {
            content = content.substring(0, 2 * SUMMARY_MAX_LENGTH);
        }
        // 去除<>内容
        content = content.replaceAll("<.*>", "");
        // 去除()内容
        content = content.replaceAll("\\(.*\\)", "");
        // 去除[]内容
        content = content.replaceAll("\\[.*]", "");
        // 去除{}内容
        content = content.replaceAll("\\{.*}", "");
        // 去除残损的标签
        content = content.replaceAll("<.*", "");
        // 去除空白
        content = content.replaceAll("\\s*", "");
        // 去除换行
        content = content.replaceAll("\r\n|\n\r|\r|\n", "");
        //针对Markdown
        content = content.replaceAll("#|`|<|>", "");
        if (content.length() > SUMMARY_MAX_LENGTH) {
            return content.substring(0, SUMMARY_MAX_LENGTH);
        }
        return content;
    }
}
