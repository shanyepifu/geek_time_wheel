package com.geek.wheel.service.redis;

public interface RedisService {

    /**
     * 计算执行圈数
     * @param key
     * @param step
     * @return
     */
    Long counter(String key, Long step);
}
