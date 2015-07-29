package com.business.lofter.entity;

import com.authority.entity.BaseEntity;


/**
 * 用户模块
 * @author daxinxin
 */
public class TestUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;
    
    private String name;
    
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

}