package xyz.yhhu.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.yhhu.blog.mapper.SendCommentMapper;
import xyz.yhhu.blog.entity.SendComment;
import xyz.yhhu.blog.service.SendCommentService;
@Service
public class SendCommentServiceImpl extends ServiceImpl<SendCommentMapper, SendComment> implements SendCommentService{

}
