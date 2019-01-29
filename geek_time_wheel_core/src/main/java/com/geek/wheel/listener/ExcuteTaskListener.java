package com.geek.wheel.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * @DESCRIPTION: 执行任务方法
 * @CLASSNAME: ExcuteTaskListener
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/1/29 15:24
 */
@Service
public class ExcuteTaskListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Queue<Integer> taskQueue;

    public void excute(Integer index){
        boolean isOffer = taskQueue.offer(index);
        if (isOffer){
            logger.debug("Task id already send to queue ...");
        } else {
            logger.debug("Task id already full ...");
        }
    }
}
