package com.geek.wheel.config;

import com.geek.wheel.TimeWheel;
import com.geek.wheel.listener.CountCycleListener;
import com.geek.wheel.listener.ExcuteTaskListener;
import com.geek.wheel.util.TimeUnitConverter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @DESCRIPTION: 时间轮默认配置
 * @CLASSNAME: TimingWheelConfiguration
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/1/29 14:34
 *
 */
@Configuration
@ConditionalOnWebApplication
public class TimeWheelWebConfiguration implements InitializingBean, DisposableBean {

    @Value("${spring.timingwheel.tickDuration}")
    private Integer tickDuration;

    @Value("${spring.timingwheel.tickDurationTimeUnit}")
    private String tickDurationTimeUnit;

    @Value("${spring.timingwheel.ticksPerWheel}")
    private Integer ticksPerWheel;

    @Autowired
    private CountCycleListener countCycleListener;

    @Autowired
    private ExcuteTaskListener excuteTaskListener;

    @Bean
    public TimeWheel timingWheel(){
        TimeWheel timingWheel = new TimeWheel(ticksPerWheel, tickDuration, TimeUnitConverter.convert(tickDurationTimeUnit));
        timingWheel.setCountCycleListener(countCycleListener);
        timingWheel.setExcuteTaskListener(excuteTaskListener);
        return timingWheel;
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        timingWheel().start();
    }

    @Override
    public void destroy() throws Exception {
        timingWheel().shuttdown();
    }
}
