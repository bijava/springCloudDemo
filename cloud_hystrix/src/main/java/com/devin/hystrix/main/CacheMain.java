package com.devin.hystrix.main;

import com.devin.hystrix.command.CacheCommand;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * 缓存
 * 在一起请求中，多个地方调用同一个接口，可考虑使用缓存
 * 需要用到CommandKey
 * 要在一起请求中执行：要初始化上下文
 */
public class CacheMain {

    // 定义一个key
    private static final String cacheKey = "cache-key";

    public static void main(String[] args) {
        // 初始化请求上下文，告知：这是一次请求
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();

        // 创建命令，开始执行
        CacheCommand cacheCommand1 = new CacheCommand(CacheMain.cacheKey);
        CacheCommand cacheCommand2 = new CacheCommand(CacheMain.cacheKey);
        CacheCommand cacheCommand3 = new CacheCommand(CacheMain.cacheKey);

        /**
         * 在不调用清空缓存的清空下，run只会执行一次
         * 如果清空缓存后，下一次执行命令马上回执行一次run
         */
        cacheCommand1.execute();
        cacheCommand2.execute();
        // 调用清空缓存
        CacheMain.clearCache();
        cacheCommand3.execute();

        System.out.println("命令cacheCommand1是否读取缓存："
                + cacheCommand1.isResponseFromCache());

        System.out.println("命令cacheCommand2是否读取缓存："
                + cacheCommand2.isResponseFromCache());

        System.out.println("命令cacheCommand3是否读取缓存："
                + cacheCommand3.isResponseFromCache());

        // 关闭
        ctx.shutdown();
    }

    /**
     * 清空缓存
     */
    public static void clearCache() {
        HystrixRequestCache cache = HystrixRequestCache.getInstance(
                HystrixCommandKey.Factory.asKey("MyCommoandKey"),
                HystrixConcurrencyStrategyDefault.getInstance());
        cache.clear(cacheKey);

    }
}
