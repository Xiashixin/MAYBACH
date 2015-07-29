package com.authority.dao;

import java.util.List;

import com.authority.entity.User;
import com.authority.entity.UserRole;


public interface UserMapper {

    /**
     * 新增用户
     * @param user
     */
    public void createUser(User user);
    
    /**
     * 查询用户集合
     * @return
     */
    public List<User> queryUsers();
    
    
    /**
     * 根据用户id查询用户信息和角色信息
     * @param id
     * @return
     */
    public User queryUserById(String id);
    
    /**
     * 通过用户的id删除用户(可批量)
     * @param ids
     */
    public void deleteUsers(String ids);
    
    /**
     * 修改用户信息
     * @param tempUser
     */
    public void updateUser(User tempUser);
    
    /**
     * 用户关联角色信息
     * @param userRole
     */
    public void relateRole(UserRole userRole);
}
