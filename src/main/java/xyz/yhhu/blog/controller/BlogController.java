package xyz.yhhu.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yhhu.blog.entity.Blog;
import xyz.yhhu.blog.entity.BlogContent;
import xyz.yhhu.blog.entity.TagName;
import xyz.yhhu.blog.entity.TypeName;
import xyz.yhhu.blog.entity.vo.BlogVO;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.BlogContentService;
import xyz.yhhu.blog.service.BlogService;
import xyz.yhhu.blog.service.TagNameService;
import xyz.yhhu.blog.service.TypeNameService;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tiger
 * @date 2020-07-15
 * @see xyz.yhhu.blog.controller
 **/
@RestController
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogContentService blogContentService;
    @Autowired
    private TagNameService tagNameService;
    @Autowired
    private TypeNameService typeNameService;

    @GetMapping()
    public Result<IPage<Blog>> list(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    @RequestParam(required = false) String keyword) {
        IPage<Blog> iPage = new Page<>(page, size);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(keyword), "title", keyword);
        queryWrapper.like(!StringUtils.isEmpty(keyword), "summary", keyword);
        queryWrapper.orderByDesc("create_date");
        return ResultGenerator.genSuccessResult(blogService.page(iPage, queryWrapper));
    }

    @GetMapping("tag")
    public Result<IPage<Blog>> listByTag(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size,
                                         @RequestParam(name = "tagId", defaultValue = "0") Integer id) {
        IPage<Blog> iPage = new Page<>(page, size);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.or().like(id != null, "tag", id + ",%");
        queryWrapper.or().like(id != null, "tag", "%," + id + ",%");
        queryWrapper.or().like(id != null, "tag", "%," + id);
        queryWrapper.or().like(id != null, "tag", id.toString());
        queryWrapper.orderByDesc("create_date");
        return ResultGenerator.genSuccessResult(blogService.page(iPage, queryWrapper));
    }

    @GetMapping("{id}/detail")
    public Result<BlogVO> detail(@PathVariable(name = "id") Integer id) {
        Blog blog = blogService.getById(id);
        BlogContent blogContent = blogContentService.getById(id);
        BlogVO blogVO = new BlogVO();
        BeanUtils.copyProperties(blog, blogVO);
        blogVO.setContent(blogContent.getContent());
        // 设置博客标签
        List<TagName> tags = tagNameService.listByIds(Arrays.asList(blog.getTag().split(",")));
        StringBuilder sb = new StringBuilder();
        for (TagName tag : tags) {
            sb.append(tag.getName()).append(",");
        }
        String tagStr = sb.toString();
        if (StringUtils.isEmpty(tagStr)) {
            blogVO.setTag("");
        } else {
            blogVO.setTag(tagStr.substring(0, tagStr.length() - 1));
        }
        // 设置博客类型
        TypeName blogType = typeNameService.getById(blog.getType());
        blogVO.setTypeName(blogType.getName());
        return ResultGenerator.genSuccessResult(blogVO);
    }

    @GetMapping("about")
    public Result<BlogVO> about() {
        return detail(1);
    }

    @GetMapping("sort")
    public Result<IPage<Blog>> listByClickSort(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size) {
        IPage<Blog> iPage = new Page<>(page, size);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("click_count");
        return ResultGenerator.genSuccessResult(blogService.page(iPage, queryWrapper));
    }

    @PostMapping("click")
    public void addClick(Integer blogId) {
        Blog blog = blogService.getById(blogId);
        if (blog != null) {
            blog.setClickCount(blog.getClickCount() + 1);
            blogService.updateById(blog);
        }
    }

    @PostMapping("like")
    public void addLike(Integer blogId) {
        Blog blog = blogService.getById(blogId);
        if (blog != null) {
            blog.setLikeCount(blog.getLikeCount() + 1);
            blogService.updateById(blog);
        }
    }
}
