package com.blog.blogBack.service.impl;

import com.blog.blogBack.dao.BlogContentMapper;
import com.blog.blogBack.entity.BlogContent;
import com.blog.blogBack.framework.service.AbstractService;
import com.blog.blogBack.service.BlogContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class BlogContentServiceImpl extends AbstractService<BlogContent> implements BlogContentService {
    @Autowired
    private BlogContentMapper blogContentMapper;
}
