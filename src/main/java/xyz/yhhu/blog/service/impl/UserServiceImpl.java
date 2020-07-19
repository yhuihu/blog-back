package xyz.yhhu.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.yhhu.blog.mapper.UserMapper;
import xyz.yhhu.blog.entity.User;
import xyz.yhhu.blog.service.UserService;
@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
