package com.authority.service;

import java.util.List;
import java.util.Map;

import com.authority.dm.AuthorityVO;
import com.authority.entity.Account;
import com.authority.entity.Module;
import com.authority.entity.Role;
import com.authority.entity.User;

public interface AuthorityService {

    public Map<String, Module> getModuleByRole(List<Role> roleList);
    
    public AuthorityVO getAuthority(Account account, User user, Map<String, Module> moduleMap);
}