package xyz.yhhu.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yhhu.blog.entity.Project;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.ProjectService;

/**
 * @author Tiger
 * @date 2020-07-19
 * @see xyz.yhhu.blog.controller
 **/
@RestController
@RequestMapping("project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping()
    public Result<IPage<Project>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        IPage<Project> iPage = new Page<>(page, size);
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("project_order");
        IPage<Project> page1 = projectService.page(iPage, queryWrapper);
        return ResultGenerator.genSuccessResult(page1);
    }
}
