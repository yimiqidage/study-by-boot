package com.spring4.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Creator weishi8 <br/>
 * Date&Time 2019-07-04 16:30 <br/>
 * description 自定义密码解析类，不加密，明文解析
 */
public class SpitterPasswordEncoder implements PasswordEncoder {
    /**
     * 不进行任何加密，直接明文返回
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return String.valueOf(rawPassword);
    }

    /**
     * 使用明文进行对比
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return String.valueOf(rawPassword).equals(encodedPassword);
    }
}
