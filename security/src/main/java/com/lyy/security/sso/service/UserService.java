package com.lyy.security.sso.service;

import com.lyy.auth.entity.Account;
import com.lyy.authclient.AuthClient;
import dto.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    private AuthClient authClient;

    public Account getUser(String username){
        Account account = new Account();
        ApiResult result = authClient.getUser(username);
        HashMap map = (HashMap)result.getData();
        Long id = Long.parseLong(map.get("id").toString());
        account.setId(id);
        account.setUsername((String) map.get("username"));
        account.setPassword((String) map.get("password"));
        account.setRole((String) map.get("role"));
        return account;
    }
}
