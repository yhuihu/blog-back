package com.blog.blogBack.service.impl;


import com.blog.blogBack.dao.BlogTagMapper;
import com.blog.blogBack.entity.BlogTag;
import com.blog.blogBack.framework.service.AbstractService;
import com.blog.blogBack.service.BlogTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by CodeGenerator on 2019/07/08.
 */
@Service
@Transactional
public class BlogTagServiceImpl extends AbstractService<BlogTag> implements BlogTagService {
    @Resource
    private BlogTagMapper tagNameMapper;

}
