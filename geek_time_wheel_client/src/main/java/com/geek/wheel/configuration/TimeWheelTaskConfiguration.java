package com.geek.wheel.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/***
 * @Description 时间轮任务配置
 * @author FX.li
 * @date 2020/2/27 13:30
 */
@Configuration
public class TimeWheelTaskConfiguration implements EnvironmentAware {

    /**
     * 客户机信息上报
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {

    }
}
