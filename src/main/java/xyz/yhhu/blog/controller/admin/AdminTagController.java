package xyz.yhhu.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yhhu.blog.entity.TagName;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.TagNameService;

import javax.annotation.Resource;

/**
 * @author Tiger
 * @date 2020-07-21
 * @see xyz.yhhu.blog.controller.admin
 **/
@RestController
@RequestMapping("/admin/tag")
public class AdminTagController {
    @Resource
    private TagNameService tagNameService;

    @GetMapping("")
    public Result list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        IPage<TagName> iPage=new Page<>(page,size);
        QueryWrapper<TagName> queryWrapper=new QueryWrapper<>();
        return ResultGenerator.genSuccessResult(tagNameService.page(iPage,queryWrapper));
    }

    @PostMapping("")
    public Result add(TagName blogTag) {
        if(StringUtils.isBlank(blogTag.getName())) {
            return ResultGenerator.genFailResult("博客标签名称不能为空！");
        }
        tagNameService.save(blogTag);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("")
    public Result delete(@RequestParam Integer id) {
        tagNameService.removeById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("")
    public Result update(TagName blogTag) {
        tagNameService.updateById(blogTag);
        return ResultGenerator.genSuccessResult();
    }
}
