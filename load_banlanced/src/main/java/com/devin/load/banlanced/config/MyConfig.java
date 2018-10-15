package com.devin.load.banlanced.config;

import java.util.Collections;
import java.util.List;

import com.devin.load.banlanced.annotation.MyLoadBalanced;
import com.devin.load.banlanced.interceptor.MyInterceptor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Autowired(required = false)
    @MyLoadBalanced
    private List<RestTemplate> restTemplates = Collections.emptyList();

    // 创建一个Bean，为每一个RestTemplate设置一个拦截器，拦截器中实现负载均衡
    // 在Spring启动之后为restTemplates添加拦截器

    // SmartInitializingSingleton为初始化的一个Bean

    @Bean
    public SmartInitializingSingleton lbInitializingSingleton() {

        return new SmartInitializingSingleton() {

            @Override
            public void afterSingletonsInstantiated() {
                // 我们先尝试在Spring启动时输出RestTemplate的个数
                // System.out.println(restTemplates.size());

                // 为所有的restTemplates设置自定义拦截器
                for (RestTemplate restTemplate : restTemplates) {

                    // 在原来拦截器的基础上添加自定义拦截器
                    List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = restTemplate
                            .getInterceptors();
                    clientHttpRequestInterceptors.add(new MyInterceptor());

                    restTemplate.setInterceptors(clientHttpRequestInterceptors);

                }
            }
        };
    }
}