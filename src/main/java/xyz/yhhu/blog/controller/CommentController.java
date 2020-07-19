package xyz.yhhu.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yhhu.blog.entity.Blog;
import xyz.yhhu.blog.entity.Comment;
import xyz.yhhu.blog.entity.Reader;
import xyz.yhhu.blog.entity.SendComment;
import xyz.yhhu.blog.entity.User;
import xyz.yhhu.blog.entity.dto.CommentReaderDTO;
import xyz.yhhu.blog.entity.vo.CommentVO;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.BlogService;
import xyz.yhhu.blog.service.CommentService;
import xyz.yhhu.blog.service.ReaderService;
import xyz.yhhu.blog.service.SendCommentService;
import xyz.yhhu.blog.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Tiger
 * @date 2020-07-16
 * @see xyz.yhhu.blog.controller
 **/
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private UserService userService;
    @Autowired
    private SendCommentService sendCommentService;

    @GetMapping()
    public Result listByBlogId(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, @Validated Integer blogId) {
        if (blogId == null) {
            return ResultGenerator.genFailResult("blogId不能为空！");
        }
        if (blogService.getById(blogId) == null) {
            return ResultGenerator.genFailResult("不存在id为" + blogId + "的博客！");
        }
        IPage<Comment> iPage = new Page<>(page, size);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("comment_date");
        queryWrapper.eq("blog_id", blogId);
        IPage page1 = commentService.page(iPage, queryWrapper);
        List<Comment> list = page1.getRecords();
        List<CommentVO> result = new ArrayList<>();
        for (Comment comment : list) {
            result.add(getCommentVo(comment));
        }
        return ResultGenerator.genSuccessResult(page1.setRecords(result));
    }

    @PostMapping()
    public Result addComment(@Valid CommentReaderDTO commentReaderDTO) {
        Integer blogId = commentReaderDTO.getBlogId();
        Blog blog = blogService.getById(blogId);
        if (blog == null) {
            return ResultGenerator.genFailResult("不存在id为" + blogId + "的博客！");
        }
        Reader receiver = null;
        Integer replyCommentId = commentReaderDTO.getReplyCommentId();
        if (replyCommentId != null) {
            Comment replyComment = commentService.getById(replyCommentId);
            if (replyComment == null) {
                return ResultGenerator.genFailResult("不存在id为" + replyCommentId + "的评论");
            }
            Integer receiverId = replyComment.getReaderId();
            receiver = readerService.getById(receiverId);
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
        blogService.updateById(blog);
        // 发送邮件通知管理员和被回复者
        User admin = userService.getById(1);
        SendComment sendComment = new SendComment();
        sendComment.setEmail(admin.getEmail());
        sendComment.setTitle(blog.getTitle());
        sendComment.setCommentId(comment.getId());
        sendComment.setBlogId(blog.getId());
        sendComment.setContent(commentReaderDTO.getContent());
        sendComment.setReaderName(commentReaderDTO.getName());
        sendCommentService.save(sendComment);
        return ResultGenerator.genSuccessResult();
    }

    private CommentVO getCommentVo(Comment comment) {
        CommentVO commentVO = new CommentVO();
        BeanUtils.copyProperties(comment, commentVO);
        Reader reader = readerService.getById(comment.getReaderId());
        commentVO.setReaderAvatar(reader.getAvatar());
        commentVO.setReaderName(reader.getName());
        Integer replyCommentId = comment.getReplyCommentId();
        if (replyCommentId != null) {
            Comment replyComment = commentService.getById(replyCommentId);
            commentVO.setReceiverDate(replyComment.getCommentDate());
            commentVO.setReceiverContent(replyComment.getContent());
            Reader receiver = readerService.getById(replyComment.getReaderId());
            commentVO.setReceiverName(receiver.getName());
            commentVO.setReceiverAvatar(receiver.getAvatar());
        }
        return commentVO;
    }
}
