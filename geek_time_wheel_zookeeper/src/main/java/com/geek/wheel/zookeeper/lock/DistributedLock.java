package com.geek.wheel.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @DESCRIPTION: 基于Zookeeper实现分布式锁
 * @CLASSNAME: DistributedLockService
 * @AUTHOR: Geek Lee
 * @DATETIME: 2019/3/5 15:43
 */
public class DistributedLock implements Lock, Watcher {

    private Logger logger = LoggerFactory.getLogger(DistributedLock.class);

    private ZooKeeper zooKeeper;

    /**
     * 锁节点所在目录
     */
    private String root;

    /**
     * 锁节点分格符
     */
    private static final String SPLIT_STR = "_lock_";

    /**
     * 当前锁
     */
    private String CURRENT_LOCK;

    /**
     * 竞争的资源
     */
    private String lockName;


    private String getLockPath(){
        return root + "/locks/" + lockName;
    }

    private String getRootLockPath(){
        return root + "/locks";
    }

    /**
     * 分布式锁构造函数，期间解决是否在根节点问题
     * @param zooKeeper
     * @param root
     * @param lockName
     */
    public DistributedLock(ZooKeeper zooKeeper, String root, String lockName){
        this.zooKeeper = zooKeeper;
        this.lockName = lockName;
        this.root = root;

        Stat stat = null;
        try {
            stat = zooKeeper.exists(getRootLockPath(), false);
            if (stat == null) {
                // 如果根节点不存在，则创建根节点
                zooKeeper.create(getRootLockPath(), new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            logger.debug("lock root {} init success", getRootLockPath());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private String checkMinNode(String currentNode){
        String lastNode = "";


        // 获取所有的子节点
        List<String> subNodes = zooKeeper.getChildren(getRootLockPath(), false);

        // 获取LockName的节点集合
        List<String> lockObjects = new ArrayList<String>();
        for(String node :subNodes){
            if (node.contains(lockName)){
                lockObjects.add(node);
            }
        }

        // 节点排序
        Collections.sort(lockObjects);

        // 判断当前节点是不是


    }


    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try {
            CURRENT_LOCK = zooKeeper.create(getLockPath() + SPLIT_STR, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.debug("locker {} already create", CURRENT_LOCK);




        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * Zookeeper 监听
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
