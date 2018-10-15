package com.devin.ribbonclient.config;

import java.util.List;

import com.netflix.client.ClientFactory;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.client.http.RestClient;

public class MyPingConfig {

    public static void main(String[] args) throws InterruptedException {
        // 设置请求的服务器
        ConfigurationManager.getConfigInstance().setProperty(
                "first-cloud-server.ribbon.listOfServers",
                "localhost:8761,localhost:8888");

        // 配置 Ping 处理类
        ConfigurationManager.getConfigInstance().setProperty(
                "first-cloud-server.ribbon.NFLoadBalancerPingClassName",
                PingUrl.class.getName());

        // 配置 Ping 时间间隔
        ConfigurationManager.getConfigInstance().setProperty(
                "first-cloud-server.ribbon.NFLoadBalancerPingInterval", 2);

        // 获取 REST 请求客户端
        RestClient client = (RestClient) ClientFactory
                .getNamedClient("first-cloud-server");

        Thread.sleep(6000);

        // 获取全部服务器
        List<Server> servers = client.getLoadBalancer().getAllServers();
        System.out.println(servers.size());

        // 输出状态
        for (Server s : servers) {
            System.out.println(s.getHostPort() + " 状态：" + s.isAlive());
        }

    }
}
