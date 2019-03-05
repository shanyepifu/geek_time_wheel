package com.geek.wheel.zookeeper.path;

import com.geek.wheel.zookeeper.ZookeeperConnectionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @DESCRIPTION: 路径管理工具
 * @CLASSNAME: PathOperateService
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/3/5 14:59
 */
@Service
public class PathOperateService {

    @Autowired
    private ZookeeperConnectionService zookeeperConnectionService;

    /**
     * 创建永久节点
     * @param path
     * @param value
     * @return
     */
    public String createPersistentPath(String path, String value) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = zookeeperConnectionService.getConnection();
        // 空节点
        value = StringUtils.isBlank(value) ? "" : value;
        String createdPath = zooKeeper.create(path,value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        return createdPath;
    }

    /**
     * 创建临时节点
     * @param path
     * @param value
     * @return
     * @throws IOException
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String createEphemeralPath(String path, String value) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = zookeeperConnectionService.getConnection();
        // 空节点
        value = StringUtils.isBlank(value) ? "" : value;
        String createdPath = zooKeeper.create(path,value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        return createdPath;
    }


}
