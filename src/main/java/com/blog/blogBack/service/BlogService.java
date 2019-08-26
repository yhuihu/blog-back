package com.blog.blogBack.service;
import com.blog.blogBack.entity.Blog;
import com.blog.blogBack.framework.service.IService;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/07/07.
 */
public interface BlogService extends IService<Blog> {

    Integer saveAndGetId(Blog blog);

    List<Blog> findByKeyword(String keyword);

    List<Blog> findAllByTagId(Integer tagId);
}
