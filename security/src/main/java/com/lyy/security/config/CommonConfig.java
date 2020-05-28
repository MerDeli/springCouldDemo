//package com.lyy.security.config;
//
//import org.codehaus.jackson.map.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class CommonConfig {
//
//    /**
//     * 密码加密类
//     *
//     * @return
//     */
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//
//    /*
//     * 加密类
//     *@return org.springframework.security.crypto.password.PasswordEncoder
//     *
//     *@author wangxulong duanxian0402@126.com
//     *@version 20191130000
//     *
//     **/
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new MessageDigestPasswordEncoder("MD5");
//    }
//
//    /*
//     * JSON 工具类
//     *@return com.fasterxml.jackson.databind.ObjectMapper
//     *
//     *@author wangxulong duanxian0402@126.com
//     *@version 20191130000
//     *
//     **/
//    @Bean
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper();
//    }
//
//
//}
