package com.geek.demo.configure;

import com.geek.wheel.client.TimeWheelClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname TaskConfigure
 * @Description TODO
 * @Date 2019-04-17 00:34
 * @Created by Geek.Li <ifaxin@yeah.net>
 */
@Configuration
public class TaskConfigure implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public TimeWheelClient timeWheelClient() throws Exception{
        TimeWheelClient timeWheelClient = new TimeWheelClient();
        timeWheelClient.setConnectionInfo("127.0.0.1:2181");
        timeWheelClient.setClientPort(8080);
        timeWheelClient.setClientIp("127.0.0.1");
        timeWheelClient.setRpcServerPort(8081);
        return timeWheelClient.build(this.applicationContext);
    }
}
