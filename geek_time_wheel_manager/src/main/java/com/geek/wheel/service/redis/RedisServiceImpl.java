package com.geek.wheel.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @DESCRIPTION: Redis
 * @CLASSNAME: RedisServiceImpl
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/1/29 15:45
 */
@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Long counter(String key, Long step){
        Long count = redisTemplate.opsForValue().increment(key , step);
        return count;
    }
}
