package com.lyy.auth.service;

import com.lyy.auth.dao.AccountDao;
import com.lyy.auth.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public Account getUser(String username){
        return accountDao.findByUsername(username);
    }
}
