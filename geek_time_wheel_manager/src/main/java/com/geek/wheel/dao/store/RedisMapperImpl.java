package com.geek.wheel.dao.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @DESCRIPTION: Redis
 * @CLASSNAME: RedisServiceImpl
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/1/29 15:45
 */
@Service
public class RedisMapperImpl implements RedisMapper {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Long counter(String key, Long step){
        Long count = redisTemplate.opsForValue().increment(key , step);
        return count;
    }
}
