package com.authority.entity;

/**
 * 角色拥有的菜单
 * @author Administrator
 */
public class RoleModule extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private Role role;
    
    private Module module;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}