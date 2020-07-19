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
import xyz.yhhu.blog.entity.Carousel;
import xyz.yhhu.blog.framework.Result;
import xyz.yhhu.blog.framework.ResultGenerator;
import xyz.yhhu.blog.service.CarouselService;


/**
 * @author Tiger
 * @date 2020-07-19
 * @see xyz.yhhu.blog.controller
 **/
@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @GetMapping("")
    public Result list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        IPage<Carousel> iPage=new Page<>(page,size);
        QueryWrapper<Carousel> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("image_order");
        return ResultGenerator.genSuccessResult(carouselService.page(iPage,queryWrapper));
    }
}
