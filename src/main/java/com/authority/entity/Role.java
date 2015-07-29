package com.authority.entity;

import java.util.List;

/**
 * 角色模块
 * @author Administrator
 */
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;
    
    private String name;
    
    private int moduleCount;
    
    //关联模块集合
    private List<Module> moduleList;
    
    //关联用户集合
    private List<User> userList;

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

    public int getModuleCount() {
        return moduleCount;
    }

    public void setModuleCount(int moduleCount) {
        this.moduleCount = moduleCount;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}