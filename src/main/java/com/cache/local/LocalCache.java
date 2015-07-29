package com.cache.local;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 本地缓存的主要作用是存储一些常用到的系统表信息，比如说账号、权限之类的
 * 本地缓存的生命周期是application级别，占用空间来自于应用程序的内存
 * 缓存级别：一级缓存
 * @author daxinxin
 */
public class LocalCache {

    /**
     * 使用ConcurrentMap做缓存的原因有几点：
     * 1. 线程安全，有重入锁来保障
     * 2. 原子操作，多线程必备
     * */
    private static ConcurrentMap<String, Object> localCache = new ConcurrentHashMap<String, Object>();

    /**
     * 更新缓存信息
     * @param key
     * @param value
     */
    public static void refreshLocalCache(String key, Object value) {
        localCache.put(key, value);
    }
    
    /**
     * 新增缓存信息
     * @param key
     * @param value
     */
    public static void put(String key, Object value){
        //实现方式一，判断key是否存在
//        if(!isExist(key))
//            refreshLocalCache(key, value);
        //实现方式二，自带方法
        localCache.putIfAbsent(key, value);
    }
    
    /**
     * 根据key获得值
     * @param key
     */
    public static Object get(String key){
        return localCache.get(key);
    }
    
    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public static boolean isExist(String key){
        if(localCache.containsKey(key))
            return true;
        return false;
    }
    
    /**
     * 根据key删除值
     * @param key
     */
    public static void remove(String key){
        if(isExist(key)){
            localCache.remove(key);
        }
    }
}