package xyz.yhhu.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.yhhu.blog.mapper.ReaderMapper;
import xyz.yhhu.blog.entity.Reader;
import xyz.yhhu.blog.service.ReaderService;
@SuppressWarnings({"ALL", "AlibabaClassMustHaveAuthor"})
@Service
public class ReaderServiceImpl extends ServiceImpl<ReaderMapper, Reader> implements ReaderService{

}
