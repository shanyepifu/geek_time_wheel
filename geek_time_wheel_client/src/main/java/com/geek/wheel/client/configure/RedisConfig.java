package com.geek.wheel.client.configure;

import com.geek.wheel.client.consumer.TaskConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @DESCRIPTION: Redis配置
 * @CLASSNAME: RedisConfig
 * @AUTHOR: Geek Lee
 * @DATETIME: 2018/10/23 9:26
 */
@Configuration
public class RedisConfig {

    @Resource
    private TaskConsumer taskConsumer;
    /**
     * redis 配置 RedisTemplate
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String,String>();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer<Object> longRedisSerializer = new GenericToStringSerializer(Long.class);// Long类型不可以会出现异常信息;
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();// Long类型不可以会出现异常信息;
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(longRedisSerializer);
        return redisTemplate;
    }

    /**
     * Redis Pub/Sub 配置 topic
     * @return
     */
    @Bean
    public ChannelTopic channelTopic(){
        return new ChannelTopic("gtw_task_pub_sub_channel");
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        Map<MessageListener, Collection<? extends Topic>> listener = new HashMap<>();
        listener.put(taskConsumer, Arrays.asList(channelTopic()));
        redisMessageListenerContainer.setMessageListeners(listener);
        redisMessageListenerContainer.setConnectionFactory(factory);
        return redisMessageListenerContainer;
    }
}
