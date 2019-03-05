package com.geek.wheel.listener;

import com.geek.wheel.constants.RedisConstants;
import com.geek.wheel.dao.store.RedisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @DESCRIPTION: 计算圈数
 * @CLASSNAME: CountCycleListener
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/1/29 15:21
 */
@Service
public class CountCycleListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisMapper redisMapper;

    public void countCycle(){
        Long step = redisMapper.counter(RedisConstants.CYCLE_COUNTER, 1L);
        logger.debug("Time wheel already excute " + step + " cycle");
    }

}
