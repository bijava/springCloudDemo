package com.devin.eruakeclient.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/*
 * 需要配置一下：让Spring容器知道这个Filter我们需要用到
 *
 * urlPatterns="/*"，拦截全部请求
 *
 * filterName="hystrixFilter"，过滤器名称
 */
@Configuration
@WebFilter(urlPatterns = "/*", filterName = "hystrixFilter")
public class MyFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        // 对每一个请求进行拦截，拦截之后干什么呢？

        System.out.println("开始执行过滤器....");

        // 在这里初始化了上下文（缓存服务时需要上下文信息）
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();

        try {

            chain.doFilter(request, response);

        } catch (Exception e) {

        } finally {
            ctx.shutdown();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
