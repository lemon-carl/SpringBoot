package com.mmall.service.impl;

import com.google.common.base.Joiner;
import com.mmall.beans.CacheKeyConstants;
import com.mmall.service.SysCacheService;
import com.mmall.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;

/**
 * @Auth CarlLing
 * @Date 2019/2/11 16:57
 * @Version 1.0
 * @Desc
 */
@Service
@Slf4j
public class SysCacheServiceImpl implements SysCacheService {


    @Resource(name = "redisPool")
    private RedisPool redisPool;

    @Override
    public String getFromCache(CacheKeyConstants prefix, String... keys) {
        ShardedJedis shardedJedis = null;
        String cacheKey = generateCacheKey(prefix,keys);

        try{
            shardedJedis = redisPool.instance();
            String value = shardedJedis.get(cacheKey);
            log.info("cacheKey ===>:" + value);
            return value;
        }catch (Exception e){
            log.error("get from cache exception, prefix:{}, keys:{}", prefix.name(), JsonMapper.obj2String(keys), e);
            return null;
        }finally{
            redisPool.safeColse(shardedJedis);
        }

    }

    public void saveCache(String toSavedValue, int timeoutSeconds, CacheKeyConstants prefix) {
        saveCache(toSavedValue, timeoutSeconds, prefix, null);
    }

    @Override
    public void saveCache(String toSavedValue, int timeoutSeconds, CacheKeyConstants prefix, String... keys) {
        if (toSavedValue == null){
            return;
        }

        ShardedJedis shardedJedis = null;
        try{
            String cacheKey = generateCacheKey(prefix, keys);
            shardedJedis  = redisPool.instance();
            shardedJedis.setex(cacheKey,timeoutSeconds,toSavedValue);
        } catch (Exception e){
            log.error("save cache exception, prefix:{}, keys:{}", prefix.name(), JsonMapper.obj2String(keys), e);
        } finally{
            redisPool.safeColse(shardedJedis);
        }
    }





    /**
     * 生成缓存key
     * @param prefix
     * @param keys
     * @return
     */
    private String generateCacheKey(CacheKeyConstants prefix, String... keys) {
        String key = prefix.name();
        if ( keys !=null && keys.length > 0){
            key += "_" + Joiner.on("_").join(keys);
        }
        return key;
    }
}
