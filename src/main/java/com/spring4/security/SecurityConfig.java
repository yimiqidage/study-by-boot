package com.spring4.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * Creator weishi8
 * Date&Time 2019-07-03 20:04
 * description Spring Security配置
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig  {
//    public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private DataSource dataSource;
//
//    /**
//     * 通过重载，配置user-detail服务
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//
//        //测试，使用内存验证用户信息
//        configureInMemory(auth);
//
//    }
//
//    /**
//     * 基于内存的用户信息存储：适用于开发环境
//     * @param auth
//     * @throws Exception
//     */
//    private void configureInMemory(AuthenticationManagerBuilder auth) throws Exception{
//        //1、基于内存的用户信息存储
//        auth.inMemoryAuthentication()
//                //使用自定义的密码认证服务
//                .passwordEncoder(new SpitterPasswordEncoder())
//                .withUser("zhangsan").password("000").roles("USER").and()
//                .withUser("lisi").password("111").roles("USER","ADMIN").and()
//                // roles("USER","ADMIN") = authorities("ROLE_USER","ROLE_ADMIN")
//                // 即：roles方法，会自动添加ROLE_ 前缀
//                .withUser("wangwu").password("222").authorities("ROLE_USER","ROLE_ADMIN")
//                ;
//    }
//
//    /**
//     * 基于数据库的用户信息存储
//     * @param auth
//     * @throws Exception
//     */
//    private void configJDBC(AuthenticationManagerBuilder auth) throws Exception{
//        auth.jdbcAuthentication()
////                .dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,status from users where username=?")
//                .authoritiesByUsernameQuery("select u.username,r.name from users u left join user_role ur on u.id=ur.user_id left join roles r on ur.role_id = r.id where u.username=? ");
//                // 如果需要解密，可以使用 passwordEncoder() 方法，该方法的参数为对应的加密方式；如果没有合适的，可以自己实现，继承 AbstractPasswordEncoder 或者自己实现 PasswordEncoder 接口；
//                // 该接口中没有解密方法（不需要解密），只有加密、以及验证匹配的接口即可。
////                .passwordEncoder(new StandardPasswordEncoder("53crt"));
//    }
//
//    private void configLDAP(AuthenticationManagerBuilder auth) throws Exception{
//
//    }
//
//    /**
//     * 使用用户自定义的认证服务
//     * @param auth
//     * @throws Exception
//     */
//    private void configUserDefined (AuthenticationManagerBuilder auth) throws Exception{
//            auth.userDetailsService(new SpitterUserService());
//    }
//    /**
//     * 通过重载，配置Spring Security的Filter链
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
////        web.addSecurityFilterChainBuilder()
//        super.configure(web);
//    }
//
//    /**
//     * 通过重载，配置如何通过拦截器保护请求
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //启用默认的登录页
//        http.formLogin();
//
//        //启动HTTP Basic认证，如果是第一次请求，会跳转到登录页面，要求输入用户名、密码进行验证。
//        //如果验证不通过，将会返回403状态，提示拒绝
//        http.httpBasic().realmName("api-realm0");
//
////        http.rememberMe().tokenValiditySeconds(20);
//
//        http.authorizeRequests()
//
//                // ant 风格表达式，对含有 /spitter/me 路径的请求，进行认证，不限制请求方式
//                .antMatchers("/spitter/me","/view/**","/spitter/me2/**").authenticated()
//                // ant 风格表达式，对get方式提交的，含有 spitters路径的请求，进行认证
//                .antMatchers(HttpMethod.GET,"/spitters/me3").authenticated()
//                // 正则表达式风格（/spitter/me3/.*）效果等同于ANT风格（/spitter/me3/**）
//                .regexMatchers("/spitter/me3/.*").authenticated()
//                // 使用SpEL表达式；通过查看源码，hasRole的方法，实际上也是转换为SpEL表达式了
////                .antMatchers("/spitter/me5").hasRole("ADMIN")
//                .antMatchers("/spitter/me5").access("hasRole('ROLE_USER') and hasRole('ROLE_ADMIN')")
//                // anyRequest 代表其他的任何请求,perimitAll 代表不认证
//                .anyRequest().permitAll();
//
//        //requiresSecure：对 /spitter/https/me5 强制要求使用https
//        http.requiresChannel().antMatchers("/spitter/https/me5").requiresSecure();
//        //requiresInsecure：对 /spitter/http/me5 强制使用http
//        http.requiresChannel().antMatchers("/spitter/http/me5").requiresInsecure();
//
//        //关闭跨站请求伪造服务（Cross-site Request Forgery)，默认是开启的。
////        http.csrf().disable();
//    }
//
//    /**
//     * 生成 BasicAuthenticationEntryPoint 对象, 在该对象的支持下, 通过验证的请求, 返回的response 将会自动加上
//     * WWW-Authenticate Header.  在该对象的支持下, 未通过验证的请求, 返回的 response 为 UNAUTHORIZED 错误.
//     *
//     * @return
//     */
//    public AuthenticationEntryPoint getAuthenticationEntryPoint(String realmName){
//        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
//        entryPoint.setRealmName(realmName);
//        return entryPoint;
//    }
}
