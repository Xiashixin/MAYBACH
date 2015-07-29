package com.authority.dao;

import com.authority.entity.Account;

public interface AccountMapper {

    /**
     * 创建账号
     * @param account
     */
    public void createAccount(Account account);
    
    
    /**
     * 验证用户名身份
     * @param account
     * @return
     */
    public Account valiAccount(Account account);
    
    /**
     * 修改密码
     * @param account
     */
//    public void updatePassword(Account account);
}