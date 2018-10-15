package com.devin.ribbonclient.rule;

import java.util.ArrayList;
import java.util.List;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;

public class MyTestPingUrl {

    public static void main(String[] args) throws InterruptedException {
        // 创建负载均衡器
        BaseLoadBalancer lb = new BaseLoadBalancer();

        // 添加服务器
        List<Server> servers = new ArrayList<Server>();

        // 8080 端口连接正常
        servers.add(new Server("localhost", 8762));
        // 一个不存在的端口
        servers.add(new Server("localhost", 8888));

        lb.addServers(servers);

        // 设置 IPing 实现类
        lb.setPing(new PingUrl());

        // 设置 Ping 时间间隔为 2 秒
        lb.setPingInterval(2);

        Thread.sleep(6000);

        for (Server s : lb.getAllServers()) {
            System.out.println(s.getHostPort() + " 状态：" + s.isAlive());
        }
    }

}
