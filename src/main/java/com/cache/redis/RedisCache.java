package com.cache.redis;

import java.util.List;

import net.minidev.json.JSONValue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Redis缓存是独立于业务系统的缓存，缓存空间由独立服务器来提供，需要单独进行管理
 * 缓存级别：二级缓存
 * @author daxinxin
 */
public class RedisCache {

    private static ApplicationContext app; 
    private static ShardedJedisPool pool;
    private static ShardedJedis jedis;
    
    static {
        app = new ClassPathXmlApplicationContext("spring-redis.xml");
        pool = (ShardedJedisPool)app.getBean("shardedJedisPool");
        jedis = pool.getResource();
    }
    
    /*☆☆☆  String类型的操作  start  ☆☆☆ */
    /**
     * 新增，如果存在就不做操作
     * @param key
     * @param value
     */
    public static void setStr(String key, String value){
        //实现方式一，判断key是否存在
//        if(!jedis.exists(key))
//            jedis.set(key, value);
        //实现方式二，自带方法
        jedis.setnx(key, value);
    }
    
    /**
     * 根据key删除
     * @param key
     */
    public static void delStr(String key){
        jedis.del(key);
    }
    
    /**
     * 更新值，如果已存在就覆盖
     * @param key
     * @param value
     */
    public static void updateStr(String key, String value){
        jedis.set(key, value);
    }
    
    
    /**
     * 根据key获得value
     * @param key
     * @return
     */
    public static String getStr(String key) {
        return  jedis.get(key);
    }
    /*☆☆☆  String类型的操作  end  ☆☆☆ */
    
    /*☆☆☆  Object类型的操作  start  ☆☆☆ */
    /**
     * 缓存对象类型的值，通过json转换为字符串
     * @param key
     * @param t
     */
    public static void setObj(String key, Object obj) {
        jedis.setnx(key, JSONValue.toJSONString(obj));
    }
    
    /**
     * 更新缓存
     * @param key
     * @param obj
     */
    public static void updateObj(String key, Object obj) {
        jedis.set(key, JSONValue.toJSONString(obj));
    }
    
    /**
     * 根据key获取对象，转换为json对象
     * @param key
     * @return
     */
    public static Object getObj(String key){
        String str = jedis.get(key);
        return JSONValue.parse(str);
    }
    /*☆☆☆  Object类型的操作  end  ☆☆☆ */
    
    /*☆☆☆  List类型的操作  start  ☆☆☆ */
    
    /**
     * 新增操作
     * @param key
     * @param list
     */
    public static  void setList(String key, List<Object> list) {
        jedis.setnx(key, JSONValue.toJSONString(list));
    }
    
    /**
     * 更新操作
     * @param key
     * @param list
     */
    public static void updateList(String key, List<Object> list) {
        jedis.set(key, JSONValue.toJSONString(list));
    }
    
    /**
     * 获取List对象
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Object> getList(String key){
        String str = jedis.get(key);
        return (List<Object>)JSONValue.parse(str);
    }
    
    /*☆☆☆  List类型的操作  end  ☆☆☆ */
    
    /**
     * 释放对象池
     */
    @SuppressWarnings("deprecation")
    public static void returnResource(){
        pool.returnResource(jedis);
    }
}