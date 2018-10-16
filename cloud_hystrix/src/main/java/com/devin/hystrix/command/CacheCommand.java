package com.devin.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

/**
 * 缓存命令
 */
public class CacheCommand extends HystrixCommand<String> {

    public String cacheKey;

    public CacheCommand(String cacheKey) {
        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("GroupKey"))
                .andCommandKey(
                        HystrixCommandKey.Factory.asKey("MyCommoandKey")));
        this.cacheKey = cacheKey;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("run()");
        return "success";
    }

    @Override
    protected String getFallback() {
        System.out.println("getFallback()");
        return "fallback";
    }

    @Override
    protected String getCacheKey() {
        return this.cacheKey;
    }

}
