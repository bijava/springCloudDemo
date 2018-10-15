package com.devin.ribbonclient.config;

import com.devin.ribbonclient.rule.MyRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

/**
 * 配置自定义规则
 * @author devin
 *
 */
public class MyConfig {

    @Bean
    public IRule getRule(){
        return new MyRule();
    }

    /**
     * 问题：如何让Spring容器知道，有这么一个配置类存在呢？
     */
}
