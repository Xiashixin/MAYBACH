package com.authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authority.dao.UserMapper;
import com.authority.entity.User;
import com.authority.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    /** 
     * 根据id获取用户信息
     */
    @Override
    public User queryUserById(String id) {
        return userMapper.queryUserById(id);
    }

    
}
