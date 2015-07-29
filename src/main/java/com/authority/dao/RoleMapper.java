package com.authority.dao;

import java.util.List;

import com.authority.entity.Role;

public interface RoleMapper {

    /**
     * 创建角色
     * @param account
     */
    public void createRole(Role role);
    
    /**
     * 查询角色
     * @return
     */
    public List<Role> queryRole(String name);
    
    
    /**
     * 根据id查询角色以及相关的菜单信息
     * @param id
     * @return
     */
    public Role queryRoleById(String id);
    
    
    /**
     * 根据多个角色id查询角色信息和所拥有模块信息(权限)
     * @param ids
     * @return
     */
    public List<Role> queryRoleByIds(String ids);
    
    /**
     * 修改角色
     * @param account
     */
    //public void updateRole(Role role);
    
    /**
     * 删除角色
     * @param ids
     */
    //public void deleteRole(String ids);
    
}