package com.devin.feign;

import com.devin.feign.client.HelloClient;
import com.devin.feign.client.MyFeignClient;
import feign.Feign;
import feign.gson.GsonEncoder;

public class FeignClientMain {

    public static void main(String[] args) {

        // 获取服务接口
        HelloClient helloClient = Feign.builder().encoder(new GsonEncoder())
                .client(new MyFeignClient())
                .target(HelloClient.class, "http://localhost:8080");

        // 请求Hello world接口
        String result = helloClient.sayHello();

        System.out.println("接口响应内容：" + result);
    }
}
