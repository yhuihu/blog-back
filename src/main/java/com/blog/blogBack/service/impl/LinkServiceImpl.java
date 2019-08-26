package com.blog.blogBack.service.impl;


import com.blog.blogBack.dao.LinkMapper;
import com.blog.blogBack.entity.Link;
import com.blog.blogBack.framework.service.AbstractService;
import com.blog.blogBack.service.LinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by CodeGenerator on 2019/07/07.
 */
@Service
@Transactional
public class LinkServiceImpl extends AbstractService<Link> implements LinkService {
    @Resource
    private LinkMapper linkMapper;

}
