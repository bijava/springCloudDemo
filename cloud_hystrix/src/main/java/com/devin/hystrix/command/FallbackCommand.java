package com.devin.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 强制打开断路器
 * @author devin
 */
public class FallbackCommand extends HystrixCommand<String> {

    public FallbackCommand() {
        // 设置组名、打开断路器(不推荐代码打开断路器，实际开发中是实现特定逻辑才打开断路器，如频繁请求同一个服务均失败才打开)
        super(HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withCircuitBreakerForceOpen(true)));// 强制打开断路器
    }

    @Override
    protected String run() throws Exception {
        return "success";
    }

    @Override
    protected String getFallback() {
        return "fallback";
    }
}