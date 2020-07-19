package xyz.yhhu.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.yhhu.blog.entity.TagName;
import xyz.yhhu.blog.mapper.TagNameMapper;
import xyz.yhhu.blog.service.TagNameService;
@Service
public class TagNameServiceImpl extends ServiceImpl<TagNameMapper, TagName> implements TagNameService{

}
