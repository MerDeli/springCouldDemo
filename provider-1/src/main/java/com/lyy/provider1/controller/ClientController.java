package com.lyy.provider1.controller;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 简要描述
 *
 * @author luyong 1406557342@qq.com
 * @version 20191220000
 */
@RestController
@RequestMapping("/provider1")
public class ClientController {
    @GetMapping("/normal")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String normal( ) {
        return "用户页面";
    }

    @GetMapping("/medium")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String medium() {
        return "这也是用户页面";
    }

    @GetMapping("/admin")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String admin() {
        return "管理员页面";
    }
}
