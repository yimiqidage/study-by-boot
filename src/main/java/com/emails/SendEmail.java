package com.emails;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

@SpringBootApplication
@SpringBootTest
public class SendEmail {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private JavaMailSenderImpl mailSender;

//    /**
//     * 发送包含简单文本的邮件
//     */
//    @Test
//    public void sendTxtMail() {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        // 设置收件人，寄件人
//        simpleMailMessage.setTo(new String[]{"2501902696@qq.com", "1587072557@qq.com"});
//        simpleMailMessage.setFrom("15611823564@163.com");
//        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
//        simpleMailMessage.setText("这里是一段简单文本。");
//        // 发送邮件
//        mailSender.send(simpleMailMessage);
//
//        System.out.println("邮件已发送");
//    }

    /**
     * 发送包含HTML文本的邮件
     *
     * @throws Exception
     */
    @Test
    public void sendHtmlMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo("563961284@qq.com");
        mimeMessageHelper.setFrom("shiwei8b402@126.com");
        mimeMessageHelper.setSubject("test 邮件测试【HTML】"+System.currentTimeMillis());

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 发送邮件
        mailSender.send(mimeMessage);

        System.out.println("邮件已发送");

    }

}

