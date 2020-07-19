package xyz.yhhu.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Tiger
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableAsync
@MapperScan("xyz.yhhu.blog.mapper")
public class BlogBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogBackApplication.class, args);
    }

}
