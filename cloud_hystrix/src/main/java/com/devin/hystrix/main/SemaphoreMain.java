package com.devin.hystrix.main;

import com.devin.hystrix.command.MyCommand;
import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;

/**
 * 信号量隔离策略
 */
public class SemaphoreMain {

    public static void main(String[] args) throws Exception {
        // 信号量策略，默认最大并发数10
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.command.default.execution.isolation.strategy",
                ExecutionIsolationStrategy.SEMAPHORE);

        // 设置最大并发数为2
        ConfigurationManager
                .getConfigInstance()
                .setProperty(
                        "hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests",
                        2);

        for (int i = 0; i < 6; i++) {
            final int index = i;
            Thread t = new Thread() {
                public void run() {
                    MyCommand c = new MyCommand(index);
                    c.execute();
                }
            };
            t.start();
        }

        Thread.sleep(5000);
    }
}
