package com.devin.hystrix.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class MyTimeOutConfig extends HystrixCommand<String> {

    public MyTimeOutConfig() {
        // 第二种方法：
        // super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));

        // 超时时间设置成2s
        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(2000)));

        // 第一种方法：设置超时时间(设置成2s)
        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(
                20000);
    }

    @Override
    protected String run() throws Exception {
        // 手动延迟3s
        Thread.sleep(3000);
        System.out.println("执行命令...");
        return "Success";
    }

    // 回退方法
    @Override
    protected String getFallback() {
        System.out.println("执行回退...");
        // return super.getFallback();
        return "Fallback...";
    }

}
