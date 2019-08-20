package com.spring4.email;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import java.util.Set;

/**
 * Creator weishi8
 * Date&Time 2019-07-11 17:57
 * description
 */
@Configuration
public class EmailConfiguration {


    /**
     * 1、使用 ClassLoaderTemplateResolver 声明模板解析器；
     * 2、
     * @return
     */
    @Bean
    public ClassLoaderTemplateResolver classLoaderTemplateResolver(){
        //1、ClassLoaderTemplateResolver 会从类根目录下查找文件；
        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
        //2、设置模板的默认位置为templates，这样在解析的时候，就不用添加templates路径了。
        classLoaderTemplateResolver.setPrefix("templates/");
        classLoaderTemplateResolver.setTemplateMode("HTML5");
        classLoaderTemplateResolver.setCharacterEncoding("UTF-8");
        //3、如果有多个模板解析器，可以设置顺序。
        classLoaderTemplateResolver.setOrder(1);
        classLoaderTemplateResolver.setCacheable(false);
        return classLoaderTemplateResolver;
//        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
//        springResourceTemplateResolver.setPrefix("templates/email/");
//        springResourceTemplateResolver.setOrder(1);
//        springResourceTemplateResolver.setCharacterEncoding("UTF-8");
//        springResourceTemplateResolver.setTemplateMode("HTML5");
//        return springResourceTemplateResolver;

    }

    /**
     * 设置其他的解析器，例如可以解析jsp等
     * @param servletContext
     * @return
     */
    @Bean
    public ServletContextTemplateResolver templateResolver(ServletContext servletContext){
        ServletContextTemplateResolver servletContextTemplateResolver = new ServletContextTemplateResolver(servletContext);
        servletContextTemplateResolver.setPrefix("/WEB-INF/");
        servletContextTemplateResolver.setOrder(2);
        return servletContextTemplateResolver;
    }


    /**
     * 1、classLoaderTemplateResolver 做为默认解析器；
     * 2、如果有多个解析器，可以将 templateEngine 的入参修改为 Set<ITemplateResolver> templateResolvers;
     * 3、如果想使用指定的bean，可以在入参前面，使用 Qualifier 注解
     * @param templateResolver
     * @return
     */
    @Bean
    public SpringTemplateEngine templateEngine(@Qualifier("classLoaderTemplateResolver") ITemplateResolver templateResolver){
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(templateResolver);
        return springTemplateEngine;
    }

    /**
     * 使用Bean的方式，配置邮件发送服务器信息
     * @param environment
     * @return
     */
    @Bean
    public JavaMailSender javaMailSender(Environment environment){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setHost(environment.getProperty("spring.mail.host"));
//        javaMailSender.setPassword(environment.getProperty("spring.mail.password"));
//        javaMailSender.setUsername(environment.getProperty("spring.mail.username"));
//        javaMailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
//        javaMailSender.setProtocol(environment.getProperty("spring.mail.protocol"));
//        javaMailSender.setDefaultEncoding(environment.getProperty("spring.mail.default-encoding"));
        return javaMailSender;
    }

//    /**
//     * 使用Bean的方式，配置邮件发送服务器信息
//     * @param session
//     * @return
//     */
//    @Bean
//    public MailSender mailSender(Session session){
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setSession(session);
//        return javaMailSender;
//    }
//
//    /**
//     * JDNI 的声明
//     *
//     * @return
//     */
//    @Bean
//    @Order(1)
//    public ServletWebServerFactory servletContainer(){
//        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory(){
//            @Override
//            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
//                tomcat.enableNaming();
//                return super.getTomcatWebServer(tomcat);
//            }
//
//            @Override
//            protected void postProcessContext(Context context) {
//                ContextResource resource = new ContextResource();
//                resource.setName("mail/Session");
//                resource.setType(Session.class.getName());
//                resource.setProperty("spring.mail.host", "smtp.126.com");
//                resource.setProperty("spring.mail.password", "df12345e612312d");
//                resource.setProperty("spring.mail.username", "shiwei8b402@126.com");
//                resource.setProperty("spring.mail.port","25");
//                resource.setProperty("spring.mail.protocol","smtp");
//                resource.setProperty("spring.mail.default-encoding","UTF-8");
//                context.getNamingResources().addResource(resource);
//                System.out.println("init JNDI "+resource.getName());
//                super.postProcessContext(context);
//            }
//        };
//        return tomcatServletWebServerFactory;
//    }
//
//    @Bean
//    @Order(2)
//    public JndiObjectFactoryBean session(){
//        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//        jndiObjectFactoryBean.setJndiName("mail/Session");
//        jndiObjectFactoryBean.setExpectedType(Session.class);
//        jndiObjectFactoryBean.setResourceRef(true);
//        System.out.println("inti mailSession...");
//        return jndiObjectFactoryBean;
//    }

}
