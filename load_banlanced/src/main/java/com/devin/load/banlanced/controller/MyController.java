package com.devin.load.banlanced.controller;

import com.devin.load.banlanced.annotation.MyLoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class MyController {

    @Bean
    @MyLoadBalanced
    RestTemplate restTemplateA(){
        return new RestTemplate();
    }

    /*@Bean
    @MyLoadBalanced
    RestTemplate restTemplateB(){
        return new RestTemplate();
    }*/


    @GetMapping("/router")
    @ResponseBody
    public String router() {
        RestTemplate restTemplate = restTemplateA();

        // 根据应用名称调用服务，这个 URI 会被拦截器所置换
        String json = restTemplate.getForObject(
                "http://user-service/hello", String.class);

        return json;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        RestTemplate restTemplate = restTemplateA();

        // 根据应用名称调用服务，这个 URI 会被拦截器所置换
        String json = restTemplate.getForObject(
                "http://user-service/hi", String.class);

        return json;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }
}
