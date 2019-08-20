package com.spring4.email;

import org.apache.http.impl.execchain.TunnelRefusedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;

/**
 * Creator weishi8
 * Date&Time 2019-07-11 17:57
 * description
 * 1、发送普通邮件，使用 MailSender 以及 JavaMailSender 接口均可；
 * 2、发送带附件邮件，需要使用 JavaMailSender 接口。
 * 3、JavaMailSender实例的注入，在 EmailConfiguration 配置文件中。
 * 4、在 EmailConfiguration 中，注入的bean名称为"javaMailSender"，在Controller中调用的时候，使用的民称为"mailSender" ，说明默认是根据类型注入的。
 *
 */
@Controller
public class EmailDemo {

    /**
     * 发送邮件，使用JavaMailSender接口，MailSender 接口只有两个简单的send方法
     */
//    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

//    @Autowired
//    private JndiObjectFactoryBean jndiObjectFactoryBean;

    /**
     * 发送普通邮件
     * @param content
     * @return
     */
    @GetMapping("/email/send")
    @ResponseBody
    public String sendEmail(@RequestParam String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("subjetct_demo");
        simpleMailMessage.setText(content);
        simpleMailMessage.setTo("weishi8@creditease.cn");
        simpleMailMessage.setFrom("shiwei8b402@126.com");
        mailSender.send(simpleMailMessage);
        return "success";
    }

    /**
     * 发送带附件的邮件
     * @param content
     * @return
     * @throws MessagingException
     */
    @ResponseBody
    @GetMapping("/email/send/attachment")
    public String sendEmailAttachment(@RequestParam String content) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("shiwei8b402@126.com");
        helper.setTo("shiwei8b402@126.com");
        helper.setSubject("subjetct_demo_attachment");
        // 如果需要加载在classpath下的文件，可以使用ClassPathResource
        helper.addAttachment("ddd.png",new File("/Users/weishi8/Desktop/QQ20190702-112234.png"));
        helper.setText(content);
        mailSender.send(helper.getMimeMessage());
        return "success";
    }


    /**
     * 1、使用 thymeleaf 模板，发送邮件。
     * 2、不要使用@ResponseBody注解，否则不会走模板解析，直接返回字符串；
     * 3、springTemplateEngine 通过 配置文件注入；
     * @param content
     * @return
     * @throws MessagingException
     */
    @GetMapping("/email/send/thymeleaf")
    public String sendEmailThymeleaf(@RequestParam String content, HttpServletRequest request) throws MessagingException {
        String from ="shiwei8b402@126.com";
        Context context = new Context();
        context.setVariable("from",from);
        context.setVariable("content",content);
        String contextHtml = springTemplateEngine.process("email/thymeleaf/emailTemplate.html",context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(from);
        helper.setSubject("subjetct_demo_attachment");
        // 如果需要加载在classpath下的文件，可以使用ClassPathResource
        helper.addAttachment("ddd.png",new File("/Users/weishi8/Desktop/QQ20190702-112234.png"));
        helper.setText(contextHtml,true);
        mailSender.send(helper.getMimeMessage());
        request.setAttribute("from",from);
        return "email/thymeleaf/emailTemplate.html";
    }

}
