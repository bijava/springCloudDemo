package com.devin.eruakeclient.controller;

import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class MyInvokerController {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/myRouter")
    @ResponseBody
    public String router() {
        RestTemplate restTemplate = getRestTemplate();

        // 根据应用名称调用服务
        String json = restTemplate.getForObject(
                "http://user-service/student/1002", String.class);

        return json;
    }

    // 使用SpringCloud集成Ribbon -- start

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/lb")
    @ResponseBody
    public ServiceInstance lb() {
        // 让SpringCloud集成的Ribbon帮助我们选择服务实例
        ServiceInstance serviceInstance = loadBalancerClient
                .choose("user-service");
        return serviceInstance;
    }

    // 使用SpringCloud集成Ribbon -- end

    // 查看SpringCloud中的Ribbon配置 -- start

    @Autowired
    private SpringClientFactory springClientFactory;

    @GetMapping("/fa")
    public String factory() {
        System.out.println("----  >>> 查看默认配置   <<<  ----\n");
        ZoneAwareLoadBalancer zoneAwareLoadBalancer = (ZoneAwareLoadBalancer) springClientFactory
                .getLoadBalancer("default");

        System.out.println("ICientConfig\t"
                + springClientFactory.getLoadBalancer("default").getClass()
                        .getName() + "\n");

        System.out.println("IRule\t"
                + zoneAwareLoadBalancer.getRule().getClass().getName() + "\n");

        System.out.println("ServerList\t"
                + zoneAwareLoadBalancer.getServerListImpl().getClass()
                        .getName() + "\n");

        System.out
                .println("ServerListFilter\t"
                        + zoneAwareLoadBalancer.getFilter().getClass()
                                .getName() + "\n");

        System.out.println("ILoadBalancer\t"
                + zoneAwareLoadBalancer.getClass().getName() + "\n");

        System.out.println("PingInterval\t"
                + zoneAwareLoadBalancer.getPingInterval() + "\n\n");

        System.out.println("----  >>> atm-eureka-ribbon-privoder配置   <<<  ----\n");

        ZoneAwareLoadBalancer zoneAwareLoadBalancer2 = (ZoneAwareLoadBalancer) springClientFactory
                .getLoadBalancer("atm-eureka-ribbon-privoder");

        System.out.println(" IClientConfig\t"
                + springClientFactory
                        .getLoadBalancer("atm-eureka-ribbon-privoder-provider")
                        .getClass().getName() + "\n");
        System.out.println(" IRule\t"
                + zoneAwareLoadBalancer2.getRule().getClass().getName() + "\n");

        System.out.println(" IPing\t"
                + zoneAwareLoadBalancer2.getPing().getClass().getName() + "\n");

        System.out.println(" ServerLis\t"
                + zoneAwareLoadBalancer2.getServerListImpl().getClass()
                        .getName() + "\n");

        System.out.println(" ServerListFilter\t"
                + zoneAwareLoadBalancer2.getFilter().getClass().getName()
                + "\n");

        System.out.println(" ILoadBalancer\t"
                + zoneAwareLoadBalancer2.getClass().getName() + "\n");

        System.out.println(" PingInterval\t"
                + zoneAwareLoadBalancer2.getPingInterval());

        return "";
    }

    // 查看SpringCloud中的Ribbon配置 -- end
}
