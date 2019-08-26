package com.blog.blogBack.service.impl;


import com.blog.blogBack.dao.BlogTypeMapper;
import com.blog.blogBack.entity.BlogType;
import com.blog.blogBack.framework.service.AbstractService;
import com.blog.blogBack.service.BlogTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by CodeGenerator on 2019/07/08.
 */
@Service
@Transactional
public class BlogTypeServiceImpl extends AbstractService<BlogType> implements BlogTypeService {
    @Resource
    private BlogTypeMapper typeNameMapper;

}
