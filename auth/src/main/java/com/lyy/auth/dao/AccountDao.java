package com.lyy.auth.dao;

import com.lyy.auth.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountDao extends JpaRepository<Account,Long>, JpaSpecificationExecutor<Account> {
    Account findByUsername(String username);
}
