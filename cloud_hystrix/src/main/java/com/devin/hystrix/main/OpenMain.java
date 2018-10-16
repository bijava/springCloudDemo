package com.devin.hystrix.main;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 断路器开启
 * 整个链路达到一定的阀值，默认情况下，10秒内产生超过20次请求，则符合第一个条件
 * 满足第一个条件的情况下，如果请求的错误百分比大于阀值，则会开启断路器，默认为50%
 * （如：10内发送30次请求，其中15次是失败的，则会开启断路器）
 */
public class OpenMain {

    public static void main(String[] args) {
        // 断路器配置(此处配置成：10s内大于10次请求)
        ConfigurationManager
                .getConfigInstance()
                .setProperty(
                        "hystrix.command.default.circuitBreaker.requestVolumeThreshold",
                        10);

        // 循环遍历发送请求，发送15次请求(满足第一个要求)
        for (int i = 0; i < 15; i++) {
            ErrorCommand c = new ErrorCommand();
            c.execute();
            // 输出断路器状态
            System.out.println(c.isCircuitBreakerOpen());
        }
    }

    static class ErrorCommand extends HystrixCommand<String> {

        public ErrorCommand() {
            // 如果超过500毫秒无响应就执行回退
            super(Setter.withGroupKey(
                    HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandPropertiesDefaults(
                            HystrixCommandProperties.Setter()
                                    .withExecutionTimeoutInMilliseconds(500)));
        }

        @Override
        protected String run() throws Exception {
            // 方法执行需要800毫秒，也就是说每次方法执行均是无响应的
            Thread.sleep(800);
            return "";
        }

        @Override
        protected String getFallback() {
            return "fallback";
        }

    }
}
