package com.authority.dao;

import java.util.List;

/**
 * DAO基础类
 * @author Administrator
 */
public interface BaseMapper {
    /**
     * 新增对象
     * @param user
     */
    public <T> void create(T object);
    
    /**
     * 查询对象
     * @return
     */
    public <T> List<T> query();
    
    /**
     * 修改对象
     * @param object
     */
    public <T> void update(T object);
    
    /**
     * 通过对象的id删除对象(可批量)
     * @param ids
     */
    public void delete(String ids);
    
}