package com.geek.wheel.dao.store;

public interface RedisMapper {

    /**
     * 计算执行圈数
     * @param key
     * @param step
     * @return
     */
    Long counter(String key, Long step);
}
