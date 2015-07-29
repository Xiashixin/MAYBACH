package com.authority.dao;

import java.util.List;

import com.authority.entity.Module;


public interface ModuleMapper {

    /**
     * 创建模块
     * @param account
     */
    public void createModule(Module module);
    
    /**
     * 查询模块
     * @return
     */
    public List<Module> queryModule(Module module);
    
    
    /**
     * 根据父模块的id查询子模块
     * @param id
     * @return
     */
    public List<Module> queryChildByParent(String id);
    
    /**
     * 修改模块
     * @param account
     */
    //public void updateModule(Module module);
    
    
    /**
     * 删除模块
     * @param ids
     */
    //public void deleteModule(String ids);
    
    
    
}