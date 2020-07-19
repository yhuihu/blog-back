package xyz.yhhu.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.yhhu.blog.mapper.BlogMapper;
import xyz.yhhu.blog.entity.Blog;
import xyz.yhhu.blog.service.BlogService;
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService{

}
