package xyz.yhhu.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.yhhu.blog.entity.Link;
import xyz.yhhu.blog.mapper.LinkMapper;
import xyz.yhhu.blog.service.LinkService;
@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService{

}
