package com.geek.wheel.dao.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @DESCRIPTION:
 * @CLASSNAME: RedisPubMapperImpl
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/1/30 10:48
 */
@Service
public class RedisPubMapperImpl implements RedisPubMapper {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void sendMessage(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
    }
}
