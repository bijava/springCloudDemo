package com.devin.hystrix.main;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandMetrics.HealthCounts;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 断路器关闭
 * 当断路器打开的时候，不再执行命令，直接进行回退方法，这段时间称为休眠期，默认时间为5s
 * 休眠期结束之后，会尝试性执行一次命令，此时，断路器状态处于半开状态，
 * 尝试执行成功之后，就会关闭断路器并且清空健康信息，如果失败，仍然处于关闭状态
 */
public class CloseMain {

    public static void main(String[] args) throws Exception {
        // 10秒内大于3次请求，满足第一个条件
        ConfigurationManager
                .getConfigInstance()
                .setProperty(
                        "hystrix.command.default.circuitBreaker.requestVolumeThreshold",
                        3);
        boolean isTimeout = true;
        for (int i = 0; i < 10; i++) {// 发送10次请求，一开始均为失败，则会打开断路器
            TestCommand c = new TestCommand(isTimeout);// 总会超时
            c.execute();
            // 输出健康信息
            HealthCounts hc = c.getMetrics().getHealthCounts();
            System.out.println("断路器状态：" + c.isCircuitBreakerOpen() + ", 请求数量："
                    + hc.getTotalRequests());
            if (c.isCircuitBreakerOpen()) {// 如果断路器打开
                isTimeout = false;//
                System.out.println("============  断路器打开了，等待休眠期结束");
                Thread.sleep(6000);// 等待休眠期结束，6s之后尝试性发一次请求
            }
        }
    }

    static class TestCommand extends HystrixCommand<String> {

        private boolean isTimeout;

        public TestCommand(boolean isTimeout) {
            super(Setter.withGroupKey(
                    HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandPropertiesDefaults(
                            HystrixCommandProperties.Setter()
                                    .withExecutionTimeoutInMilliseconds(500)));// 500毫秒没有响应，则打开断路器
            this.isTimeout = isTimeout;
        }

        @Override
        protected String run() throws Exception {
            if (isTimeout) {
                Thread.sleep(800);// 方法执行时间为800毫秒
            } else {
                Thread.sleep(200);
            }
            return "";
        }

        @Override
        protected String getFallback() {
            return "fallback";
        }
    }
}
