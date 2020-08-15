package xyz.yhhu.blog.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Tiger
 * @date 2020-07-22
 * @see xyz.yhhu.blog.utils
 **/
@Component
public class UploadService {

    @Autowired
    OSS ossClient;

    /**
     * 将图片上传到阿里oss
     */
    public String uploadFile(FileInputStream file, String key) throws NullPointerException {
        ossClient.putObject("zsifan", key, file);
        return "https://yhhu.oss-cn-shenzhen.aliyuncs.com/" + key;
    }
}
