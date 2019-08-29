package com.blog.blogBack.service.impl;

import com.blog.blogBack.dao.SendCommentMapper;
import com.blog.blogBack.entity.SendComment;
import com.blog.blogBack.framework.service.AbstractService;
import com.blog.blogBack.service.SendCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Service
@Transactional
public class SendCommentServiceImpl extends AbstractService<SendComment> implements SendCommentService {
    @Resource
    SendCommentMapper sendCommentMapper;
}
