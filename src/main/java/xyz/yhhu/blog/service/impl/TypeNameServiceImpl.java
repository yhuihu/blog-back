package xyz.yhhu.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.yhhu.blog.mapper.TypeNameMapper;
import xyz.yhhu.blog.entity.TypeName;
import xyz.yhhu.blog.service.TypeNameService;
@Service
public class TypeNameServiceImpl extends ServiceImpl<TypeNameMapper, TypeName> implements TypeNameService{

}
