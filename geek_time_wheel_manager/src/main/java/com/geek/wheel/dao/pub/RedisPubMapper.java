package com.geek.wheel.dao.pub;

public interface RedisPubMapper {

    void sendMessage(String channel ,String message);
}
