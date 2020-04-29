package com.lyy.security.db;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="account")
@Entity
public class Account {
    @Id
    private Long id;
    private String username;
    private String password;
    private String role;
}
