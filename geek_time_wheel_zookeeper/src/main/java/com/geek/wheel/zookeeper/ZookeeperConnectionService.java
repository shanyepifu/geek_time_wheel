package com.geek.wheel.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @DESCRIPTION:
 * @CLASSNAME: ZookeeperConnection
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/3/5 14:38
 */
@Configuration
public class ZookeeperConnectionService {

    private Logger logger = LoggerFactory.getLogger(ZookeeperConnectionService.class);

    private ConcurrentHashMap<String, ZooKeeper> zookeeperConnectionCache = new ConcurrentHashMap<>();

    private ZooKeeper zooKeeper;

    @Value("${spring.zookeeper.connection}")
    private String connectionStr;

    @Value("${spring.zookeeper.root}")
    private String rootPath;

    // 会话超时时间，设置为与系统默认时间一致
    private static final Integer SESSION_TIMEOUT = 30 * 1000;

    // 创建 Watcher 实例
    private Watcher wh = watchedEvent -> {
        logger.debug("occur change : " + watchedEvent.getPath());
    };

    /**
     * 获取Zookeeper连接
     * @return
     */
    public synchronized ZooKeeper getConnection() throws IOException {
        zooKeeper = zookeeperConnectionCache.get(connectionStr);
        if (zooKeeper == null || !zooKeeper.getState().isAlive()){
            zooKeeper = new ZooKeeper(connectionStr, SESSION_TIMEOUT, wh);
            zookeeperConnectionCache.put(connectionStr, zooKeeper);
        }
        return zooKeeper;
    }

    public String getRootPath() {
        return rootPath;
    }
}
