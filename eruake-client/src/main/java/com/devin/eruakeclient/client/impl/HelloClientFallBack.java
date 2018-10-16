package com.devin.eruakeclient.client.impl;

import com.devin.eruakeclient.client.HelloClient;
import org.springframework.stereotype.Component;

//HelloClient类去Spring容器中寻找HelloClientFallBack
//所以添加注解Component
@Component
public class HelloClientFallBack implements HelloClient {

    public String sayHello() {
        return "fallBack Hello";
    }

    public String toHello() {
        return "fallBack timeOut Hello";
    }
}
