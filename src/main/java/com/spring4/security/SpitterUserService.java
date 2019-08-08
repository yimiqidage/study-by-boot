package com.spring4.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator weishi8
 * Date&Time 2019-07-04 11:07
 * description 自定义的用户认证服务
 */
public class SpitterUserService implements UserDetailsService {

    /**
     * 实现 loadUserByUsername 接口，通过username，查找到对应的 UserDetails 对象。
     * 注意：此处的UserDetails 是一个接口，可以找到 UserDetails 的实现，如 org.springframework.security.core.userdetails.User
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority>authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        User user = new User(username,"000", authorityList);
        return user;
    }
}
