package com.lyy.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/success")
    public String login(){
        return "登录成功";
    }


    @GetMapping("/error")
    public String error(){
        return "error";
    }


    @GetMapping("/product/info")
    public String productInfo1(){
        return " some product info ";
    }


    @GetMapping("/admin/home")
    public String productInfo2(){
        return " admin home page ";
    }
}
