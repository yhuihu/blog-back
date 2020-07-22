package xyz.yhhu.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yhhu.blog.entity.Carousel;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.CarouselService;

import javax.annotation.Resource;

/**
 * @author Tiger
 * @date 2020-07-21
 * @see xyz.yhhu.blog.controller.admin
 **/
@RestController
@RequestMapping("/admin/carousel")
public class AdminCarouselController {
    @Resource
    private CarouselService carouselService;

    @PostMapping("")
    public Result add(Carousel carousel) {
        if (carousel.getImageOrder() == null) {
            return ResultGenerator.genFailResult("轮播的次序不能为空！");
        }
        if (StringUtils.isBlank(carousel.getImage())) {
            return ResultGenerator.genFailResult("轮播图片不能为空！");
        }
        if (StringUtils.isBlank(carousel.getUrl())) {
            carousel.setUrl(null);
        }
        carouselService.save(carousel);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("")
    public Result update(Carousel carousel) {
        Integer carouselId = carousel.getId();
        if (carouselId == null) {
            return ResultGenerator.genFailResult("轮播的id不能为空！");
        }
        if (carouselService.getById(carouselId) == null) {
            return ResultGenerator.genFailResult("不存在id为" + carouselId + "的轮播！");
        }
        if (StringUtils.isBlank(carousel.getUrl())) {
            carousel.setUrl(null);
        }
        if (StringUtils.isBlank(carousel.getImage())) {
            carousel.setImage(null);
        }
        carouselService.updateById(carousel);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("")
    public Result delete(@RequestParam Integer id) {
        carouselService.removeById(id);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("")
    public Result detail(@RequestParam Integer id) {
        return ResultGenerator.genSuccessResult(carouselService.getById(id));
    }
}
