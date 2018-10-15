package com.devin.eruakeclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Configuration
public class InvokerController {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value="/router",method=RequestMethod.GET,
            produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String router() {
        RestTemplate restTemplate = getRestTemplate();

        // 根据应用名称调用服务
        String json = restTemplate.getForObject("http://user-service/student/1", String.class);

        return json;
    }

    @Autowired
    public DiscoveryClient discoveryClient;

    //获取服务列表，并将服务列表的数量显示出来
    //默认是30s抓取一次，可以根据需求进行修改
    @GetMapping("/list")
    @ResponseBody
    public String serviceCount() {
        List<String> serviceNames = discoveryClient.getServices();

        for (String serviceId : serviceNames) {
            List<ServiceInstance> serviceInstance = discoveryClient
                    .getInstances(serviceId);
            System.out.println(serviceId + ": " + serviceInstance.size());
        }
        return "";
    }

    @GetMapping("/metedata")
    @ResponseBody
    public String getMetedata(){
        System.out.println("Come into getMetedata...");
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("eruake-client");

        for (ServiceInstance serviceInstance : serviceInstances) {
            String name=serviceInstance.getMetadata().get("company-name");
            System.out.println(serviceInstance.getPort()+"---->>>"+name);
        }

        return "";
    }
}
