package xyz.yhhu.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.yhhu.blog.mapper.BlogContentMapper;
import xyz.yhhu.blog.entity.BlogContent;
import xyz.yhhu.blog.service.BlogContentService;
@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@Service
public class BlogContentServiceImpl extends ServiceImpl<BlogContentMapper, BlogContent> implements BlogContentService{

}
