package xyz.yhhu.blog.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiger
 * @date 2020-07-22
 * @see xyz.yhhu.blog.config
 **/
@Configuration
public class OssConfig {
    String endpoint = "http://oss-cn-chengdu.aliyuncs.com";
    String accessKeyId = "xxx";
    String accessKeySecret = "xxx";

    @Bean
    OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
