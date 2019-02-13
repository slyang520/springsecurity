package com.example.slyangsecurity.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private  ValueOperations<String, Object> valueOperations; //String（字符串）

    private final static long DEFAULT_CACHE_TIME_DAY = 3;

    /**
     * 缓存 对象JSON 序列化
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void cacheObjectToJson(String key, Object value, long timeout, TimeUnit unit){
        valueOperations.set(key,value,timeout ,unit);
    }

    /**
     * 缓存 对象JSON 序列化
     * @param key
     * @param value
     */
    public void cacheObjectToJson(String key, Object value){
        cacheObjectToJson(key, value,DEFAULT_CACHE_TIME_DAY,TimeUnit.DAYS);
    }

    /**
     * 缓存 对象JSON 序列化
     * @param key
     * @param classT
     */
    public <T> T  getCacheObject(String key, Class<T> classT){
        return (T)valueOperations.get(key);
    }


}
