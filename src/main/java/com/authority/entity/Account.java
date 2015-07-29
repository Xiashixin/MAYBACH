package com.authority.entity;

/**
 * 账号模块
 * @author Administrator
 */
public class Account extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private String accountName;
    
    private String password;
    
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}