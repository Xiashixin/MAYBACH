package com.authority.entity;

/**
 * 用户具备的角色
 * @author Administrator
 */
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private User user;
    
    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}