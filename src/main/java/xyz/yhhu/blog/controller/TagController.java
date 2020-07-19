package xyz.yhhu.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yhhu.blog.entity.TagName;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.TagNameService;

/**
 * @author Tiger
 * @date 2020-07-18
 * @see xyz.yhhu.blog.controller
 **/
@RestController
@RequestMapping("tag")
public class TagController {
    @Autowired
    private TagNameService tagNameService;

    @GetMapping()
    public Result<IPage<TagName>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        IPage<TagName> iPage = new Page<>(page, size);
        IPage<TagName> page1 = tagNameService.page(iPage);
        return ResultGenerator.genSuccessResult(page1);
    }
}
