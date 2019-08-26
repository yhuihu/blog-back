package com.blog.blogBack.service.impl;


import com.blog.blogBack.dao.CarouselMapper;
import com.blog.blogBack.entity.Carousel;
import com.blog.blogBack.framework.service.AbstractService;
import com.blog.blogBack.service.CarouselService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by CodeGenerator on 2019/07/07.
 */
@Service
@Transactional
public class CarouselServiceImpl extends AbstractService<Carousel> implements CarouselService {
    @Resource
    private CarouselMapper carouselMapper;

}
