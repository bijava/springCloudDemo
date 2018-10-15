package com.devin.ribbonclient.client;

import com.devin.ribbonclient.rule.MyRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

public class MyRESTClient02 {

    public static void main(String[] args) {

        // 创建负载均衡器
//        ILoadBalancer iLoadBalancer = new BaseLoadBalancer();
        BaseLoadBalancer iLoadBalance = new BaseLoadBalancer();

        //设置自定义的负载规则
        iLoadBalance.setRule(new MyRule(iLoadBalance));

        // 添加服务器
        List<Server> serverList = new ArrayList<Server>();
        serverList.add(new Server("localhost", 8080));
        serverList.add(new Server("localhost", 8081));

        iLoadBalance.addServers(serverList);

        //进行6次服务器选择
        for (int i = 0; i < 6; i++) {
            Server server=iLoadBalance.chooseServer(null);
            System.out.println(server);
        }
    }
}
