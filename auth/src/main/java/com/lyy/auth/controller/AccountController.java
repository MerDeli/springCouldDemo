package com.lyy.auth.controller;

import com.lyy.auth.service.AccountService;
import dto.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sso")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getUser")
    public ApiResult getUser(@RequestParam("username") String username){
        return ApiResult.success(accountService.getUser(username));
    }
}
