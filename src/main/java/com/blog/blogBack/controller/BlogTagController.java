package com.blog.blogBack.controller;
import com.blog.blogBack.entity.BlogTag;
import com.blog.blogBack.framework.Result;
import com.blog.blogBack.framework.ResultGenerator;
import com.blog.blogBack.service.BlogTagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/07/08.
 */
@RestController
@RequestMapping("/blog/tag")
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

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
