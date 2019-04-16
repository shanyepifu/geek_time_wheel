package com.geek.zookeeper;

import com.geek.constants.ZookeeperConstants;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname ZookeeperClient
 * @Description zookeeper 客户端
 * @Date 2019-04-16 22:51
 * @Created by Geek.Li <ifaxin@yeah.net>
 */
public class ZookeeperClient {

    /**
     * 创建zookeeper客户端缓存
     */
    private static final ConcurrentHashMap<String, CuratorFramework> clientCache = new ConcurrentHashMap<>();

    /**
     * 设置默认重试策略
     */
    private static RetryPolicy retryPolicy = new ExponentialBackoffRetry(ZookeeperConstants.SLEEP_TIME, ZookeeperConstants.MAX_RETRIES);


    /**
     * 创建客户端
     * @param connectionInfo
     */
    public static synchronized CuratorFramework getClient(String connectionInfo){
        CuratorFramework client = clientCache.get(connectionInfo);
        if (client != null){
            return client;
        } else {
            client = CuratorFrameworkFactory.builder()
                    .connectString(connectionInfo)
                    .sessionTimeoutMs(ZookeeperConstants.SESSION_TIME_OUT)
                    .connectionTimeoutMs(ZookeeperConstants.CONNECT_TIME_OUT)
                    .retryPolicy(retryPolicy)
                    .namespace(ZookeeperConstants.NAME_SPACE)
                    .build();
            client.start();
            clientCache.put(connectionInfo, client);
            return client;
        }
    }



}
