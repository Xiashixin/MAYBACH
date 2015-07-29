package com.authority.entity;

import java.util.List;


/**
 * 用户模块
 * @author daxinxin
 */
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;
    
    private String name;
    
    private String orgId;
    
    private List<Role> roleList;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
    
}