package com.devin.zuulrouter.config;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MyConfig {

    /**
     * 访问网关的 /invoker/**，将会被路由到 atm-zuul-invoker 服务进行处理
     *
     * zuul:
     *  routes:
     *   myServer:
     *   path: /invoker/**
     *   serviceId: atm-zuul-invoker
     *
     */
    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper() {
        // 通过一个正则表达式来匹配
        return new PatternServiceRouteMapper("(atm)-(zuul)-(?<module>.+)",
                "${module}/**");
    }
}
