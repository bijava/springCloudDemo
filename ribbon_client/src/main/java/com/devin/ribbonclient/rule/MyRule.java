package com.devin.ribbonclient.rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class MyRule implements IRule {

    ILoadBalancer ibBalancer;

    public MyRule() {

    }

    public MyRule(ILoadBalancer iLoadBalancer) {
        this.ibBalancer = iLoadBalancer;
    }

    public Server choose(Object key) {
        // 获取全部的服务器
        List<Server> servers = ibBalancer.getAllServers();

        Random random = new Random();
        int randNum = random.nextInt(10);
        if(randNum>7){
            return getServerByPort(servers, 8761);
        }

        // 至返回第一个Server对象
        return getServerByPort(servers, 8762);
//        return servers.get(0);
    }

    public Server getServerByPort(List<Server> servers,int port){
        for (Server server : servers) {
            if(server.getPort()==port){
                return server;
            }
        }
        return null;
    }

    public ILoadBalancer getLoadBalancer() {
        return this.ibBalancer;
    }

    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.ibBalancer = iLoadBalancer;
    }

}
