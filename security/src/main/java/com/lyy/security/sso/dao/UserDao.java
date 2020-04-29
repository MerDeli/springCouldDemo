package com.lyy.security.sso.dao;

import com.lyy.security.db.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Account,Long>, JpaSpecificationExecutor<Account> {
    Account findByUsername(String username);
}
