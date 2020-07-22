package xyz.yhhu.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yhhu.blog.entity.Project;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.ProjectService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Tiger
 * @date 2020-07-22
 * @see xyz.yhhu.blog.controller.admin
 **/
@RestController
@RequestMapping("/admin/project")
public class AdminProjectController {
    @Resource
    private ProjectService projectService;

    @GetMapping("")
    public Result list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        IPage<Project> iPage = new Page<>(page, size);
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("project_order");
        return ResultGenerator.genSuccessResult(projectService.page(iPage, queryWrapper));
    }

    @GetMapping("{id}")
    public Result detail(@PathVariable(name = "id") Integer id) {
        Project project = projectService.getById(id);
        if (project == null) {
            return ResultGenerator.genFailResult("不存在id为" + id + "的项目！");
        }
        return ResultGenerator.genSuccessResult(project);
    }

    @PostMapping("")
    public Result add(@Valid Project project) {
        if (StringUtils.isBlank(project.getImage())) {
            project.setImage(null);
        }
        projectService.save(project);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("")
    public Result update(@Valid Project project) {
        Integer projectId = project.getId();
        if (projectId == null) {
            return ResultGenerator.genFailResult("项目id不能为空！");
        }
        if (projectService.getById(projectId) == null) {
            return ResultGenerator.genFailResult("不存在id为" + projectId + "的项目！");
        }
        //  防止保存空（但不是null，而是空格之类）信息
        if (StringUtils.isBlank(project.getImage())) {
            project.setImage(null);
        }
        projectService.updateById(project);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("")
    @CacheEvict(cacheNames = "project", allEntries = true)
    public Result delete(@RequestParam Integer id) {
        projectService.removeById(id);
        return ResultGenerator.genSuccessResult();
    }


}
