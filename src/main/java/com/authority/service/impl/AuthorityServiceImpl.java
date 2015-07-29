package com.authority.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.authority.dm.AuthorityVO;
import com.authority.entity.Account;
import com.authority.entity.Module;
import com.authority.entity.Role;
import com.authority.entity.User;
import com.authority.service.AuthorityService;

/**
 * 权限的服务类
 * @author daxinxin
 */
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {

    /**
     * 获取模块module的集合
     */
    @Override
    public Map<String, Module> getModuleByRole(List<Role> roleList) {
        //定义map,用module的id作为key,这样可以避免不同角色包含相同的module权限,如果有相同,会通过id过滤掉
        Map<String, Module> moduleMap = new HashMap<String, Module>();
        for(Role role : roleList) {
            for(Module module : role.getModuleList()) {
                //存储module的id和path
                moduleMap.put(module.getId(), module);
            }
        }
        return moduleMap;
    }

    /**
     * 封装权限对象
     */
    @Override
    public AuthorityVO getAuthority(Account account, User user,
            Map<String, Module> moduleMap) {
        AuthorityVO vo = new AuthorityVO();
        vo.setAccount(account);
        vo.setUser(user);
        vo.setModuleMap(moduleMap);
        return vo;
    }

    
}
