package com.blog.blogBack.service;

import com.blog.blogBack.entity.Comment;
import com.blog.blogBack.framework.service.IService;

import java.util.List;

/**
 * Created by CodeGenerator on 2019/07/08.
 */
public interface CommentService extends IService<Comment> {

    List<Comment> findAllByBlogId(Integer blogId);
}
