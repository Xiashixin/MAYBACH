package com.authority.entity;

/**
 * 角色拥有菜单的动作权限（如：新增、修改、删除）
 * @author Administrator
 *
 */
public class RoleOperation extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private Role role;
    
    private Operation moduleAction;
    
    private String path;

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

    public Operation getModuleAction() {
        return moduleAction;
    }

    public void setModuleAction(Operation moduleAction) {
        this.moduleAction = moduleAction;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}