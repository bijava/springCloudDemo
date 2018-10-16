package com.devin.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * 1、命令组名称
 * 2、命令名称
 * 3、线程池名称
 */
public class KeyCommand extends HystrixCommand<String> {

    public KeyCommand() {
        // 设置组名，每一个命令，Hystrix底层都会分配一个线程池去执行命令，它会使用一个map来维护这些线程池
        // 如果不提供线程池名称，则默认是使用组名作为线程池的key：Map<String,Pool>
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CommandKey"))
                .andThreadPoolKey(
                        HystrixThreadPoolKey.Factory.asKey("ThreadPoolKey")));
    }

    @Override
    protected String run() throws Exception {
        return null;
    }

}
