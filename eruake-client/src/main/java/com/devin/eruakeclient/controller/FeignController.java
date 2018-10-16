package com.devin.eruakeclient.controller;

import com.devin.eruakeclient.client.HelloClient;
import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class FeignController {

    @Autowired
    private HelloClient helloClient;

    /**
     * 退回测试
     * @return
     */
    @RequestMapping(value = "ivHello", method = RequestMethod.GET)
    public String ivHello() {
        return helloClient.sayHello();
    }

    /**
     * 断路器测试
     * @return
     */
    @RequestMapping(value = "tmHello", method = RequestMethod.GET)
    public String tmHello() {
        String result = helloClient.toHello();
        HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory
                .getInstance(HystrixCommandKey.Factory
                        .asKey("HelloClient#toHello()"));
        // System.out.println(">>>>>>>>>>\t看一下结果：\t" + result);
        System.out.println("断路器状态：" + breaker.isOpen());
        return result;
    }
}
