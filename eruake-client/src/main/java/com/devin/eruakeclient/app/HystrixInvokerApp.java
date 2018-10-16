package com.devin.eruakeclient.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 这个启动类不能正常加载其他包的Baen
 * 废弃
 */
@Deprecated
@SpringBootApplication
@EnableEurekaClient
// 打开断路器
@EnableCircuitBreaker
// 扫描
@ServletComponentScan
//打开Feign注解
@EnableFeignClients
public class HystrixInvokerApp {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(HystrixInvokerApp.class, args);
    }
}
