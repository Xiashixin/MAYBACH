package com.authority.service;

import java.util.List;

import com.authority.entity.Role;
import com.authority.entity.User;

public interface RoleService {

    public List<Role> queryRoleByUser(User user);
}