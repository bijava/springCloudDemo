package com.devin.hystrix.main;

import com.devin.hystrix.command.MyCommand;
import com.devin.hystrix.config.MyTimeOutConfig;
import com.netflix.config.ConfigurationManager;

public class HelloMain {

    public static void main(String[] args) throws Exception {
        // 请求正常的服务
//        String normalUrl = "http://localhost:8080/hello";
//        HelloCommand command = new HelloCommand(normalUrl);
//        String result = command.execute();
//        System.out.println("请求正常的服务，结果是：" + result);

        // 服务响应超时，回退
//        MyTimeOutConfig c = new MyTimeOutConfig();
//        String result1 = c.execute();
//        System.out.println(result1);

        /**
         * 默认是使用线程隔离策略
         * 设置线程池（超过3，认为是线程池满载）
         */
        ConfigurationManager.getConfigInstance().setProperty(
                "hystrix.threadpool.default.coreSize", 3);

        for (int i = 0; i < 6; i++) {
            MyCommand c = new MyCommand(i);
            c.queue();//异步执行
        }

        Thread.sleep(5000);
    }
}
