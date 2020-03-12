package com.whl.common.service;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface CacheService {

    /**
     * 添加不过期缓存，缓存对象
     * @param key
     * @param obj
     * @param <T>
     */
    <T> void setObject(String key,T obj);
    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param date 过期时间，单位/天
     * @param <T>
     */
    <T> void setObjectDay(String key,T obj,Integer date);

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/秒
     * @param <T>
     */
    <T> void setObjectSecond(String key,T obj,Integer time);

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/分钟
     * @param <T>
     */
    <T> void setObjectMinutes(String key,T obj,Integer time);

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/小时
     * @param <T>
     */
    <T> void setObjectHours(String key,T obj,Integer time);
    /**
     * 获取缓存值
     * @param key
     * @param <T>
     * @return
     */
    <T> T getObject(String key,Class<T> obj);

    /**
     * 添加不过期缓存，缓存对象
     * @param key
     * @param obj
     * @param <T>
     */
    <T> void setList(String key, List<T> obj);

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param date 过期时间，单位/天
     * @param <T>
     */
    <T> void setListDay(String key,List<T> obj,Integer date);

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/秒
     * @param <T>
     */
    <T> void setListSecond(String key,List<T> obj,Integer time);
    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/分钟
     * @param <T>
     */
    <T> void setListMinutes(String key,List<T> obj,Integer time);
    /**
     * 添加过期缓存，缓存列表
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/小时
     * @param <T>
     */
    <T> void setListHours(String key,List<T> obj,Integer time);

    /**
     * 获取缓存值
     * @param key
     * @param <T>
     * @return
     */
    <T> List<T> getList(String key,Class<T> obj);

    /**
     * 删除缓存
     * @param key
     */
    void remove(String key);

    /**
     * 删除模糊匹配
     * @param keys
     */
    void remove(Set<String> keys);
    /**
     * 模糊匹配缓存key
     * @param key
     * @return
     */
    Set<String> keys(String key);

    /**
     * 竞争set
     * @param key
     * @param value
     * @return
     */
    boolean setIfAbsent(String key, String value);

    String getKey(String k);

    Boolean expire(String key, long timeout, TimeUnit timeUnit) ;

}
