package com.geek.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.zookeeper.CreateMode;

/**
 * @Classname ZookeeperNodeOperate
 * @Description zookeeper 节点操作
 * @Date 2019-04-16 23:10
 * @Created by Geek.Li <ifaxin@yeah.net>
 */
public class ZookeeperNodeOperate {


    /**
     * 创建临时节点
     * @param client
     * @param path
     * @param value
     * @throws Exception
     */
    public static void createNode(CuratorFramework client, CreateMode createMode,String path, String value) throws Exception{
        client.create().creatingParentsIfNeeded().withMode(createMode).forPath(path, value.getBytes());
    }


    /**
     * 监控节点信息，获取节点内容
     * @param client
     * @param path
     * @param treeCacheListener
     * @throws Exception
     */
    public static void addWatcher(CuratorFramework client, String path, TreeCacheListener treeCacheListener) throws Exception{
        TreeCache treeCache=new TreeCache(client, path);
        treeCache.start();
        treeCache.getListenable().addListener(treeCacheListener);
    }


}
