package com.geek.constants;

/**
 * @Classname ZookeeperConstants
 * @Description Zookeeper 常量定义
 * @Date 2019-04-16 22:48
 * @Created by Geek.Li <ifaxin@yeah.net>
 */
public class ZookeeperConstants {

    /**
     * zookeeper 根目录
     */
    public static final String NAME_SPACE = "time_wheel";

    /**
     * 重试等待时间
     */
    public static final Integer SLEEP_TIME = 1000;

    /**
     * 最大重试次数
     */
    public static final Integer MAX_RETRIES = 3;

    /**
     * 会话超时时间
     */
    public static final Integer SESSION_TIME_OUT = 5000;

    /**
     * 链接超时时间
     */
    public static final Integer CONNECT_TIME_OUT = 5000;

}
