package com.mall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.ibatis.session.LocalCacheScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.locale.LocaleObjectCache;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2018
 *
 * @FileName: TokenCache
 * @Author: root1
 * @Date: 18-1-10 下午2:43
 * @Description: TokenCache
 * History:
 */
public class TokenCache {

    public static final String TOKEN_PREFIX = "token_";

    /**
     * 声明一个日志，用到logback
     */
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    /**
     * 声明一个静态的内存块（guawa,本地缓存）
     * initialCapacity(1000)是缓存的初始化容量
     * maximumSize(10000)是最大值，但缓存超过这个最大值时，
     * CacheBuilder会调用LRU算法清除多余的缓存
     * expireAfterAccess是缓存有效期
     */
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000)
            .expireAfterAccess(12, TimeUnit.HOURS).build(new CacheLoader<String, String>() {
                //默认的数据加载实现，当调用get取值的时候，如果key没有对应的值，就调用这个方法进行加载
                @Override
                public String load(String s) throws Exception {
                    //"null"这样写的原因是因为防止空指针异常
                    return "null";
                }
            });

    /**
     * 设置key
     * @param key
     * @param value
     */
    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getKey(String key){
        String value=null;
        try{
            value=localCache.get(key);
            if ("null".equals(key)){
                return null;
            }
        }catch (Exception e){
            logger.error("localCache get error...",e);
        }
        return null;
    }

}