package com.geek.wheel.client;

import com.geek.annotations.TimeWheelTaskProvider;
import com.geek.constants.ZookeeperConstants;
import com.geek.zookeeper.ZookeeperClient;
import com.geek.zookeeper.ZookeeperNodeOperate;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @Classname TimeWheelClient
 * @Description 时间轮客户端启动器
 * @Date 2019-04-16 23:34
 * @Created by Geek.Li <ifaxin@yeah.net>
 */
public class TimeWheelClient  {

    private String connectionInfo;

    private String clientIp;

    private Integer clientPort;

    private Integer rpcServerPort;



    public TimeWheelClient build(ApplicationContext applicationContext) throws Exception{
        // 获取zookeeper客户端
        CuratorFramework client = ZookeeperClient.getClient(connectionInfo);
        // 获取所有客户端
        Map<String,Object> beans = applicationContext.getBeansWithAnnotation(TimeWheelTaskProvider.class);
        // 注册节点
        for(Object bean:beans.values()){
            if(bean != null){
                String path = "/" + bean.getClass().getName() + "/" + clientIp + ":" + rpcServerPort;
                System.out.println(path);
                try{
                    ZookeeperNodeOperate.createNode(client, CreateMode.EPHEMERAL, path, "");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return this;
    }

    public String getConnectionInfo() {
        return connectionInfo;
    }

    public void setConnectionInfo(String connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Integer getClientPort() {
        return clientPort;
    }

    public void setClientPort(Integer clientPort) {
        this.clientPort = clientPort;
    }

    public Integer getRpcServerPort() {
        return rpcServerPort;
    }

    public void setRpcServerPort(Integer rpcServerPort) {
        this.rpcServerPort = rpcServerPort;
    }
}
