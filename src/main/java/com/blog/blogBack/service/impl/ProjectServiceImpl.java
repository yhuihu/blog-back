package com.blog.blogBack.service.impl;


import com.blog.blogBack.dao.ProjectMapper;
import com.blog.blogBack.entity.Project;
import com.blog.blogBack.framework.service.AbstractService;
import com.blog.blogBack.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by CodeGenerator on 2019/07/07.
 */
@Service
@Transactional
public class ProjectServiceImpl extends AbstractService<Project> implements ProjectService {
    @Resource
    private ProjectMapper projectMapper;

}
