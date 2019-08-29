package com.blog.blogBack.controller;

import com.blog.blogBack.entity.Blog;
import com.blog.blogBack.entity.Comment;
import com.blog.blogBack.entity.Reader;
import com.blog.blogBack.entity.User;
import com.blog.blogBack.entity.dto.CommentReaderDTO;
import com.blog.blogBack.entity.vo.CommentVO;
import com.blog.blogBack.framework.Result;
import com.blog.blogBack.framework.ResultGenerator;
import com.blog.blogBack.service.BlogService;
import com.blog.blogBack.service.CommentService;
import com.blog.blogBack.service.ReaderService;
import com.blog.blogBack.service.UserService;
import com.blog.blogBack.util.EmailTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CodeGenerator on 2019/07/07.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailTool emailTool;

    @PostMapping("/add")
    public Result add(CommentReaderDTO commentReaderDTO, HttpServletRequest request) {

        if (StringUtils.isEmpty(commentReaderDTO.getName())) return ResultGenerator.genFailResult("评论者名称不能为空！");
        if (StringUtils.isEmpty(commentReaderDTO.getContent())) return ResultGenerator.genFailResult("评论内容不能为空！");

        Integer blogId = commentReaderDTO.getBlogId();
        if (blogId == null) return ResultGenerator.genFailResult("blogId不能为空！");
        Blog blog = blogService.findById(blogId);
        if (blog == null) {
            return ResultGenerator.genFailResult("不存在id为" + blogId + "的博客！");
        }

        Reader receiver = null;
        Integer replyCommentId = commentReaderDTO.getReplyCommentId();
        if (replyCommentId != null) {
            Comment replyComment = commentService.findById(replyCommentId);
            if (replyComment == null) {
                return ResultGenerator.genFailResult("不存在id为" + replyCommentId + "的评论");
            }
            Integer receiverId = replyComment.getReaderId();
            receiver = readerService.findById(receiverId);
            if (receiver == null) {
                return ResultGenerator.genFailResult("不存在id为" + receiverId + "的读者");
            }
        }

        // 插入评论
        Comment comment = new Comment();
        comment.setBlogId(commentReaderDTO.getBlogId());
        comment.setCommentDate(new Date());
        comment.setContent(commentReaderDTO.getContent());
        comment.setReaderId(commentReaderDTO.getId());
        comment.setReplyCommentId(commentReaderDTO.getReplyCommentId());
        commentService.save(comment);

        // 更新评论数
        blog.setCommentCount(blog.getCommentCount() + 1);
        blogService.update(blog);

        // 发送邮件通知管理员和被回复者
        User admin = userService.findById(1);
        String content = commentReaderDTO.getName() + "在《" + blog.getTitle() + "》下回复\n" + commentReaderDTO.getContent();
        emailTool.sendSimpleMail(admin.getEmail(), "light blog评论", content);
        if (receiver != null) { // 仅发给管理员
            // 通知被回复者
            // 仅当receiver开启了接受邮件并且填写了邮箱时发送邮件
            if ((receiver.getInform() == 1) && StringUtils.isNotBlank(receiver.getEmail())) {
                content = commentReaderDTO.getName() + "在《" + blog.getTitle() + "》下回复了你:\n" + commentReaderDTO.getContent();
                emailTool.sendSimpleMail(receiver.getEmail(), "light blog评论回复通知", content);
            }
        }
        return ResultGenerator.genSuccessResult();
    }


    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Comment comment = commentService.findById(id);
        if (comment == null) return ResultGenerator.genFailResult("不存在id为" + id + "的评论！");
        CommentVO commentVO = getCommentVo(comment);
        return ResultGenerator.genSuccessResult(commentVO);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size, "comment_date desc");
        List<Comment> list = commentService.findAll();
        List<CommentVO> result = new ArrayList<>();
        for (Comment comment : list) {
            result.add(getCommentVo(comment));
        }

        // 给前端的是VO，但需要分页
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(result);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/list/blog")
    public Result listByBlogId(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, Integer blogId) {
        if (blogId == null) return ResultGenerator.genFailResult("blogId不能为空！");
        if (blogService.findById(blogId) == null) return ResultGenerator.genFailResult("不存在id为" + blogId + "的博客！");
        PageHelper.startPage(page, size, "comment_date desc");
        List<Comment> list = commentService.findAllByBlogId(blogId);
        List<CommentVO> result = new ArrayList<>();
        for (Comment comment : list) {
            result.add(getCommentVo(comment));
        }
        // 给前端的是VO，但需要分页
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(result);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    private CommentVO getCommentVo(Comment comment) {
        CommentVO commentVO = new CommentVO();
        commentVO.setId(comment.getId());
        commentVO.setBlogId(comment.getBlogId());
        commentVO.setCommentDate(comment.getCommentDate());
        commentVO.setContent(comment.getContent());
        commentVO.setReaderId(comment.getReaderId());
        Reader reader = readerService.findById(comment.getReaderId());
        commentVO.setReaderAvatar(reader.getAvatar());
        commentVO.setReaderName(reader.getName());
        Integer replyCommentId = comment.getReplyCommentId();
        if (replyCommentId != null) {
            Comment replyComment = commentService.findById(replyCommentId);
            commentVO.setReceiverDate(replyComment.getCommentDate());
            commentVO.setReceiverContent(replyComment.getContent());
            Reader receiver = readerService.findById(replyComment.getReaderId());
            commentVO.setReceiverName(receiver.getName());
            commentVO.setReceiverAvatar(receiver.getAvatar());
        }
        return commentVO;
    }
}
