package com.blog.blogBack.util;

import com.blog.blogBack.service.SendCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class SendEmailTasks {
    @Autowired
    SendCommentService sendCommentService;
    @Autowired
    JavaMailSender javaMailSender;
    @Scheduled(cron = "0 00 08 * * ? ")
    public void sendEmail() {
        System.out.println("现在是:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println("开始发送评论");
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 32, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        sendCommentService.findAll().forEach(item->{
            pool.execute(new ThreadEmail(item.getContent(),item.getEmail(),item.getTitle(),item.getReaderName(),item.getBlogId(),javaMailSender));
            sendCommentService.deleteById(item.getId());
        });
        System.out.println("今天的邮件已经发送完成了");
    }
}
