package com.blog.blogBack.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class ThreadEmail implements Runnable {

    private JavaMailSender mailSender;

    private String content;
    private String to;
    private String title;
    private String readerName;
    private Integer blogId;
    public ThreadEmail(String con, String t, String tit,String readerName,Integer blogId,JavaMailSender mailSender) {
        this.content = con;
        this.to = t;
        this.title = tit;
        this.readerName=readerName;
        this.blogId=blogId;
        this.mailSender=mailSender;
    }

    @Override
    public void run() {
        SimpleMailMessage message = new SimpleMailMessage();
        String from = "1357958736@qq.com";
        String blogUrl="该条博客地址：http://blog.yhhu.xyz/#/blog/";
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Blog评论");
        message.setText(readerName+ "在《" + title + "》下回复\n"+content+"\n"+blogUrl+blogId);
        mailSender.send(message);
    }
}
