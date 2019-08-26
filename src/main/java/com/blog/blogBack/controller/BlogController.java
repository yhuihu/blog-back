package com.blog.blogBack.controller;

import com.blog.blogBack.entity.Blog;
import com.blog.blogBack.entity.BlogContent;
import com.blog.blogBack.entity.vo.BlogVO;
import com.blog.blogBack.framework.Result;
import com.blog.blogBack.framework.ResultGenerator;
import com.blog.blogBack.service.BlogContentService;
import com.blog.blogBack.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: Tiger
 * @create: 2019-08-04
 **/
@RestController
@RequestMapping("/Blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogContentService blogContentService;
    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id){
        Result result=new Result();
        Blog blog = blogService.findById(id);
        BlogContent blogContent = blogContentService.findById(id);
        BlogVO blogVO = new BlogVO();
        BeanUtils.copyProperties(blog, blogVO);
        blogVO.setContent(blogContent.getContent());
        result.setData(blogVO);
        return result;
    }

    @GetMapping("/about")
    public Result about() {
        return detail(1);
    }

    @GetMapping("/search")
    public Result search(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, String keyword) {
        PageHelper.startPage(page, size);
        List<Blog> list = blogService.findByKeyword(keyword);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size, "create_date desc");
        List<Blog> list = blogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/list/tag")
    public Result listByTag(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, Integer tagId) {
        PageHelper.startPage(page, size, "create_date desc");
        List<Blog> list = blogService.findAllByTagId(tagId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/list/click")
    public Result listByClick(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size, "click_count desc");
        List<Blog> list = blogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/list/comment")
    public Result listByComment(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size, "comment_count desc");
        List<Blog> list = blogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/list/like")
    public Result listByLike(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size, "like_count desc");
        List<Blog> list = blogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/click/add")
    public Result addClick(Integer blogId) {
        Blog blog = blogService.findById(blogId);
        if (blog == null) return ResultGenerator.genFailResult("不存在id为" + blogId + "的博客！");
        blog.setClickCount(blog.getClickCount() + 1);
        blogService.update(blog);
        return ResultGenerator.genSuccessResult("增加点击数成功！");
    }

    @PostMapping("/like/add")
    public Result addLick(Integer blogId) {
        Blog blog = blogService.findById(blogId);
        if (blog == null) return ResultGenerator.genFailResult("不存在id为" + blogId + "的博客！");
        blog.setLikeCount(blog.getLikeCount() + 1);
        blogService.update(blog);
        return ResultGenerator.genSuccessResult("增加喜欢数成功！");
    }

    @PostMapping("/comment/add")
    public Result addCommentCount(Integer blogId) {
        Blog blog = blogService.findById(blogId);
        if (blog == null) return ResultGenerator.genFailResult("不存在id为" + blogId + "的博客！");
        blog.setCommentCount(blog.getCommentCount() + 1);
        blogService.update(blog);
        return ResultGenerator.genSuccessResult("增加评论数成功！");
    }
}
