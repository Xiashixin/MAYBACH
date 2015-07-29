package com.authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authority.dao.AccountMapper;
import com.authority.dm.AuthorityVO;
import com.authority.entity.Account;
import com.authority.service.AccountService;
import com.cache.local.LocalCache;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    
    /**
     * 验证用户登陆信息
     * @param account
     * @return
     */
    public Account valiAccount(String name, String pwd){
        //查询缓存是否存在，如果不存在，则检索数据库
        if(LocalCache.isExist(name)) {
            AuthorityVO authority = (AuthorityVO)LocalCache.get(name);
            Account account = authority.getAccount();
            if(name.equals(account.getAccountName()) && pwd.equals(account.getPassword()))
                return account;
            return null;
        } else {
            Account account = new Account();
            account.setAccountName(name);
            account.setPassword(pwd);
            return accountMapper.valiAccount(account);
        }
    }
}