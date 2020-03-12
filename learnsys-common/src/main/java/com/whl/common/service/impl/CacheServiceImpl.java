package com.whl.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.whl.common.service.CacheService;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Setter
public class CacheServiceImpl implements CacheService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public CacheServiceImpl(){}

    /**
     * 添加不过期缓存，缓存对象
     * @param key
     * @param obj
     * @param <T>
     */
    @Override
    public <T> void setObject(String key,T obj){
       if(obj == null){
           return;
       }
       if(StringUtils.isEmpty(key)){
           return;
       }
       String value = JSON.toJSONString(obj);
       stringRedisTemplate.opsForValue().set(key,value);
    }

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param date 过期时间，单位/天
     * @param <T>
     */
    @Override
    public <T> void setObjectDay(String key,T obj,Integer date){
        if(obj == null || date == null){
            return;
        }
        if(StringUtils.isEmpty(key)){
            return;
        }
        String value = JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullNumberAsZero);
        stringRedisTemplate.opsForValue().set(key,value,date, TimeUnit.DAYS);
    }

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/秒
     * @param <T>
     */
    @Override
    public <T> void setObjectSecond(String key,T obj,Integer time){
        if(obj == null || time == null){
            return;
        }
        if(StringUtils.isEmpty(key)){
            return;
        }
        String value = JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullNumberAsZero);
        stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/分钟
     * @param <T>
     */
    @Override
    public <T> void setObjectMinutes(String key,T obj,Integer time){
        if(obj == null || time == null){
            return;
        }
        if(StringUtils.isEmpty(key)){
            return;
        }
        String value = JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullNumberAsZero);
        stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.MINUTES);
    }

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/小时
     * @param <T>
     */
    @Override
    public <T> void setObjectHours(String key,T obj,Integer time){
        if(obj == null || time == null){
            return;
        }
        if(StringUtils.isEmpty(key)){
            return;
        }
        String value = JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullNumberAsZero);
        stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.HOURS);
    }

    /**
     * 获取缓存值
     * @param key
     * @param <T>
     * @return
     */
    @Override
    public <T> T getObject(String key,Class<T> obj){
        if(StringUtils.isEmpty(key)){
            return null;
        }
        String value = stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(value)){
           return null;
        }
        return JSON.parseObject(value,obj);
    }

    /**
     * 添加不过期缓存，缓存对象
     * @param key
     * @param obj
     * @param <T>
     */
    @Override
    public <T> void setList(String key,List<T> obj){
        if(obj == null){
            return;
        }
        if(StringUtils.isEmpty(key)){
            return;
        }
        String value = JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullNumberAsZero);
        stringRedisTemplate.opsForValue().set(key,value);
    }

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param date 过期时间，单位/天
     * @param <T>
     */
    @Override
    public <T> void setListDay(String key,List<T> obj,Integer date){
        if(obj == null || date == null){
            return;
        }
        if(StringUtils.isEmpty(key)){
            return;
        }
        String value = JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullNumberAsZero);
        stringRedisTemplate.opsForValue().set(key,value,date, TimeUnit.DAYS);
    }

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/秒
     * @param <T>
     */
    @Override
    public <T> void setListSecond(String key, List<T> obj, Integer time){
        if(obj == null || time == null){
            return;
        }
        if(StringUtils.isEmpty(key)){
            return;
        }
        String value = JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullNumberAsZero);
        stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    /**
     * 添加过期缓存，缓存对象
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/分钟
     * @param <T>
     */
    @Override
    public <T> void setListMinutes(String key,List<T> obj,Integer time){
        if(obj == null || time == null){
            return;
        }
        if(StringUtils.isEmpty(key)){
            return;
        }
        String value = JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullNumberAsZero);
        stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.MINUTES);
    }

    /**
     * 添加过期缓存，缓存列表
     * @param key 缓存key
     * @param obj 对象
     * @param time 过期时间，单位/小时
     * @param <T>
     */
    @Override
    public <T> void setListHours(String key,List<T> obj,Integer time){
        if(obj == null || time == null){
            return;
        }
        if(StringUtils.isEmpty(key)){
            return;
        }
        String value = JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullNumberAsZero);
        stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.HOURS);
    }

    /**
     * 获取缓存值
     * @param key
     * @param <T>
     * @return
     */
    @Override
    public  <T> List<T> getList(String key,Class<T> obj){
        if(StringUtils.isEmpty(key)){
            return null;
        }
        String value = stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(value)){
            return null;
        }
        return JSON.parseArray(value,obj);
    }

    /**
     * 删除缓存
     * @param key
     */
    @Override
    public void remove(String key){
        if(StringUtils.isEmpty(key)){
            return;
        }
        stringRedisTemplate.delete(key);
    }

    /**
     * 删除模糊匹配
     * @param keys
     */
    @Override
    public void remove(Set<String> keys){
        if(keys == null){
            return;
        }
        stringRedisTemplate.delete(keys);
    }

    /**
     * 模糊匹配缓存key
     * @param key
     * @return
     */
    @Override
    public Set<String> keys(String key){
        if(StringUtils.isEmpty(key)){
            return null;
        }
        return stringRedisTemplate.keys(key);
    }

    /**
     * 竞争set
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean setIfAbsent(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }
    @Override
    public String getKey(String k) {
        return stringRedisTemplate.opsForValue().get(k);
    }
    @Override
    public Boolean expire(String key, long timeout, TimeUnit timeUnit) {
         return stringRedisTemplate.expire(key, timeout, timeUnit);
    }
    

	
}
