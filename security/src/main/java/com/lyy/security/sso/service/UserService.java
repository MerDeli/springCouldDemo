package com.lyy.security.sso.service;

import com.lyy.security.db.Account;
import com.lyy.security.sso.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Account getUser(String username){
        return userDao.findByUsername(username);
    }
}
