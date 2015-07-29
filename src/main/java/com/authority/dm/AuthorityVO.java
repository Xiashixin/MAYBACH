package com.authority.dm;

import java.util.Map;

import com.authority.entity.Account;
import com.authority.entity.Module;
import com.authority.entity.User;

/**
 * 用户权限信息的VO
 * @author Administrator
 *
 */
public class AuthorityVO {

    private Account account;
    
    private User user;
    
    private Map<String, Module> moduleMap;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Module> getModuleMap() {
        return moduleMap;
    }

    public void setModuleMap(Map<String, Module> moduleMap) {
        this.moduleMap = moduleMap;
    }
}