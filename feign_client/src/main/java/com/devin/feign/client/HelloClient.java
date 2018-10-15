package com.devin.feign.client;

import com.devin.feign.annotation.MyAnnott;
import feign.RequestLine;

/**
 * 表示一个服务接口
 * @author aitemi
 *
 */
public interface HelloClient {

    /**
     * 使用了@RequestLine注解
     * 表示用GET方法，向"/hello"发送请求
     */
    @RequestLine("GET /hello")
    String sayHello();

    /**
     * 使用自定义注解
     */
//    @MyAnnott(url="/hello", method = "GET")
//    String sayHello();
}