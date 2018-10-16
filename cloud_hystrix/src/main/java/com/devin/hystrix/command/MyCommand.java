package com.devin.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class MyCommand extends HystrixCommand<String> {

    public int index;

    public MyCommand(int index) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory
                .asKey("ExampleGroup")));
        this.index = index;
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(500);
        System.out.println("run()，当前索引：" + index);
        return "success";
    }

    @Override
    protected String getFallback() {
        System.out.println("getFallback()，当前索引：" + index);
        return "fallback";
    }

}
