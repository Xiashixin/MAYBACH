package com.authority.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authority.dao.RoleMapper;
import com.authority.entity.Role;
import com.authority.entity.User;
import com.authority.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    
    /**
     * 获取用户所拥有的权限
     */
    @Override
    public List<Role> queryRoleByUser(User user) {
        return roleMapper.queryRoleByIds(this.mergeRoleId(user.getRoleList()));
    }

    
    private String mergeRoleId(List<Role> roleList){
        String ids = "";
        for(Role role : roleList){
            ids = ids + role.getId() + ",";
        }
        ids = ids + "1";
        return ids;
    }
    
}