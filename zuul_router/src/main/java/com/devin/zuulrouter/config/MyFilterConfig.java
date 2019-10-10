package com.devin.zuulrouter.config;

import com.devin.zuulrouter.filter.MyFilter;
import com.devin.zuulrouter.filter.RestTemplateFilter;
import com.devin.zuulrouter.filter.TokenFilter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 将过滤器配置进Spring容器，Spring将会注册MyFilter过滤器
 *
 */
@Configuration
public class MyFilterConfig {

    /**
     * 在过滤器中使用到RestTemplate ,所以在启动的时候加载这个Bean
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MyFilter myFilter() {
        return new MyFilter();
    }

    @Bean
    public TokenFilter  tokenFilter() { return new TokenFilter();}

    @Bean
    public RestTemplateFilter restTemplateFilter() {
        return new RestTemplateFilter();
    }
}
