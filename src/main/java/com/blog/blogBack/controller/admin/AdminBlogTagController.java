package com.blog.blogBack.controller.admin;
import com.blog.blogBack.entity.BlogTag;
import com.blog.blogBack.framework.Result;
import com.blog.blogBack.framework.ResultGenerator;
import com.blog.blogBack.service.BlogTagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/07/08.
 */
@RestController
@RequestMapping("/admin/blog/tag")
public class AdminBlogTagController {
    @Resource
    private BlogTagService blogTagService;

    @PostMapping("/add")
    @CacheEvict(cacheNames = "tag", allEntries=true)
    public Result add(BlogTag blogTag) {
        if(StringUtils.isBlank(blogTag.getName())) return ResultGenerator.genFailResult("博客标签名称不能为空！");
        blogTagService.save(blogTag);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    @CacheEvict(cacheNames = "tag", allEntries=true)
    public Result delete(@RequestParam Integer id) {
        blogTagService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @CacheEvict(cacheNames = "tag", allEntries=true)
    public Result update(BlogTag blogTag) {
        blogTagService.update(blogTag);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        BlogTag blogTag = blogTagService.findById(id);
        return ResultGenerator.genSuccessResult(blogTag);
    }

    @GetMapping("/list")
    @Cacheable(cacheNames = "tag")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<BlogTag> list = blogTagService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
