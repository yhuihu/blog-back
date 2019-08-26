package com.blog.blogBack.service.impl;

import com.blog.blogBack.dao.BlogMapper;
import com.blog.blogBack.entity.Blog;
import com.blog.blogBack.framework.service.AbstractService;
import com.blog.blogBack.service.BlogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @description:
 * @author: Tiger
 * @create: 2019-07-29
 **/
@Service
@Transactional
public class BlogServiceImpl extends AbstractService<Blog> implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Integer saveAndGetId(Blog blog) {
        blogMapper.insert(blog);
        return blog.getId();
    }

    @Override
    public List<Blog> findByKeyword(String keyword) {
        Condition condition = new Condition(Blog.class);
        Example.Criteria criteria = condition.createCriteria();
        Example.Criteria criteria1 = condition.createCriteria();
        if (StringUtils.isNotBlank(keyword)) {
            criteria.andLike("title", "%" + keyword + "%");
            criteria1.andLike("summary", "%" + keyword + "%");
            condition.or(criteria1);
        }
        return blogMapper.selectByCondition(condition);
    }

    @Override
    public List<Blog> findAllByTagId(Integer tagId) {
        Condition condition = new Condition(Blog.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.orLike("tag", tagId + ",%");
        criteria.orLike("tag", "%," + tagId + ",%");
        criteria.orLike("tag", "%," + tagId);
        criteria.orLike("tag", tagId.toString());
        return blogMapper.selectByCondition(condition);
    }
}