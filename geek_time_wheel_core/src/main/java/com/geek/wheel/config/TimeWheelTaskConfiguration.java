package com.geek.wheel.config;

import com.geek.wheel.dao.pub.RedisPubMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @DESCRIPTION: 时间轮默认配置
 * @CLASSNAME: TimingWheelConfiguration
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/1/29 14:34
 *
 */
@Configuration
@ConditionalOnWebApplication
public class TimeWheelTaskConfiguration implements InitializingBean, DisposableBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.timingwheel.maxExecuteSize}")
    private Integer maxExecuteSize;

    @Autowired
    private RedisPubMapper redisPubMapper;

    private AtomicBoolean shutdown = new AtomicBoolean(false);

    @Bean
    public Queue<Integer> taskQueue(){
        return new ArrayBlockingQueue(maxExecuteSize);
    }

    @Override
    public void destroy() throws Exception {
        if(shutdown.get()){
            logger.debug("Task queue is already shutdown...");
        } else {
            shutdown.set(true);
            logger.debug("Task queue is shutdown...");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Queue<Integer> queue = taskQueue();
        Thread excuteTaskThread = new Thread(()->{
            while (!shutdown.get()){
                Integer element = queue.poll();
                if (element != null){
                    redisPubMapper.sendMessage("gtw_task_pub_sub_channel", "task_" + element);
                    logger.info("current excute : " + element);
                }
            }
        });
        excuteTaskThread.start();
    }
}
