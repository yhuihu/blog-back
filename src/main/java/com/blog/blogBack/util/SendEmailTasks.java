package com.blog.blogBack.util;

import com.blog.blogBack.entity.SendComment;
import com.blog.blogBack.service.SendCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class SendEmailTasks {
    @Autowired
    SendCommentService sendCommentService;
    @Autowired
    JavaMailSender javaMailSender;
    @Scheduled(cron = "0 00 08 * * ? ")
    public void sendEmail() {
        System.out.println("现在是:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        StringBuilder sendText= new StringBuilder();
        String blogUrl="该条博客地址：http://blog.yhhu.xyz/#/blog/";
        List<SendComment> list=sendCommentService.findAll();
        for (SendComment item :list) {
            sendText.append(item.getReaderName()).append("在《").append(item.getTitle()).append("》下回复----")
                    .append(item.getContent()).append("----").append(blogUrl)
                    .append(item.getBlogId()).append("\n");
            sendCommentService.deleteById(item.getId());
        }
        SimpleMailMessage message = new SimpleMailMessage();
        String from = "1357958736@qq.com";
        message.setFrom(from);
        message.setTo(from);
        message.setSubject("Blog评论");
        message.setText(String.valueOf(sendText));
        javaMailSender.send(message);
        System.out.println("今天的邮件已经发送完成了");
    }
}
