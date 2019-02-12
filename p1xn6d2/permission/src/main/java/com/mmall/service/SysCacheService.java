package com.mmall.service;

import com.mmall.beans.CacheKeyConstants;

/**
 * @Auth CarlLing
 * @Date 2019/2/11 16:56
 * @Version 1.0
 * @Desc
 */
public interface SysCacheService {

    /**
     *
     * @param toSavedValue
     * @param timeoutSeconds
     * @param prefix
     */
    void saveCache(String toSavedValue, int timeoutSeconds, CacheKeyConstants prefix);

    /**
     * 保存缓存
     * @param toSavedValue
     * @param timeoutSeconds
     * @param prefix
     */
    void saveCache(String toSavedValue, int timeoutSeconds, CacheKeyConstants prefix, String... keys);

    /**
     * 获取缓存
     * @param prefix
     * @param keys
     * @return
     */
    String getFromCache(CacheKeyConstants prefix, String... keys);
}
