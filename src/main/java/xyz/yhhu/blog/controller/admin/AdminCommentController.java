package xyz.yhhu.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yhhu.blog.entity.Blog;
import xyz.yhhu.blog.entity.Comment;
import xyz.yhhu.blog.entity.Reader;
import xyz.yhhu.blog.entity.vo.AdminCommentVO;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.BlogService;
import xyz.yhhu.blog.service.CommentService;
import xyz.yhhu.blog.service.ReaderService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tiger
 * @date 2020-07-21
 * @see xyz.yhhu.blog.controller.admin
 **/
@RestController
@RequestMapping("/admin/comment")
public class AdminCommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private ReaderService readerService;
    @Resource
    private BlogService blogService;

    @GetMapping("")
    public Result list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("comment_date");
        IPage page1 = new Page<>(page, size);
        IPage page2 = commentService.page(page1, queryWrapper);
        List<Comment> list = page2.getRecords();
        List<AdminCommentVO> result = new ArrayList<>();
        for (Comment comment : list) {
            result.add(getCommentVo(comment));
        }
        // 给前端的是VO，但需要分页
        return ResultGenerator.genSuccessResult(page2.setRecords(result));
    }

    @DeleteMapping("")
    public Result delete(@RequestParam Integer id) {
        // 减少相应文章的评论数
        Comment comment = commentService.getById(id);
        if (comment == null) {
            return ResultGenerator.genFailResult("不存在id为" + id + "的评论！");
        }
        Blog blog = blogService.getById(comment.getBlogId());
        if (blog == null) {
            return ResultGenerator.genFailResult("不存在id为" + comment.getBlogId() + "的博客！");
        }
        blog.setCommentCount(blog.getCommentCount() - 1);
        blogService.updateById(blog);
        commentService.removeById(id);
        return ResultGenerator.genSuccessResult();
    }

    private AdminCommentVO getCommentVo(Comment comment) {
        AdminCommentVO adminCommentVO = new AdminCommentVO();
        adminCommentVO.setId(comment.getId());
        adminCommentVO.setBlogId(comment.getBlogId());
        adminCommentVO.setCommentDate(comment.getCommentDate());
        adminCommentVO.setContent(comment.getContent());
        Integer blogId = comment.getBlogId();
        Blog blog = blogService.getById(blogId);
        adminCommentVO.setBlogTitle(blog.getTitle());
        Reader reader = readerService.getById(comment.getReaderId());
        adminCommentVO.setReaderAvatar(reader.getAvatar());
        adminCommentVO.setReaderName(reader.getName());
        adminCommentVO.setIp(reader.getIp());
        Integer replyCommentId = comment.getReplyCommentId();
        if (replyCommentId != null) {
            Comment replyComment = commentService.getById(replyCommentId);
            adminCommentVO.setReceiverDate(replyComment.getCommentDate());
            adminCommentVO.setReceiverContent(replyComment.getContent());
            Reader receiver = readerService.getById(replyComment.getReaderId());
            adminCommentVO.setReceiverName(receiver.getName());
            adminCommentVO.setReceiverAvatar(receiver.getAvatar());
        }
        return adminCommentVO;
    }
}
