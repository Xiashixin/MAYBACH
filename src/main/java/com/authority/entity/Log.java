package com.authority.entity;

import java.io.Serializable;

/**
 * 操作日志表
 * @author Administrator
 */
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    
    private String accountName;
    
    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}