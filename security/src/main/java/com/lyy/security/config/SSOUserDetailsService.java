package com.lyy.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * TODO 简要描述
 *
 * @author luyong 1406557342@qq.com
 * @version 20191220000
 */
@Component
public class SSOUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String user = "admin";
        if(!user.equals(s)){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new User(s,passwordEncoder.encode("admin123"),
        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
