package com.devin.feign;

import com.devin.feign.client.HelloClient;
import feign.Feign;
import feign.Logger;

/**
 * 接口日志
 */
public class LogMain {

    public static void main(String[] args) {
        /**
         * NONE：    默认值，不记录日志
         * BASIC：   记录请求方法、URL、响应状态代码和执行时间
         * HEADERS： 除BASIC记录日志外，还会记录请求头和响应头的信息
         * FULL：    在HEADERS的基础上，请求和响应的元数据，都会保存
         * feign_client/logs/main.log  需要先创建好
         */
        HelloClient helloClient = Feign.builder().logLevel(Logger.Level.FULL)
                .logger(new Logger.JavaLogger().appendToFile("feign_client/logs/main.log"))
                .target(HelloClient.class, "http://localhost:8080/");

        String result = helloClient.sayHello();

        System.out.println(result);
    }
}
