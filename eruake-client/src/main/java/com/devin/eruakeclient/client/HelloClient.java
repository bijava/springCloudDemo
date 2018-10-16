package com.devin.eruakeclient.client;

import com.devin.eruakeclient.client.impl.HelloClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//当HellClien访问user-service失败时，需要有一个后备服务
//后备服务需要实现该接口
@FeignClient(name = "user-service", fallback = HelloClientFallBack.class)
public interface HelloClient {

    @RequestMapping(method=RequestMethod.GET,value="/hello")
    String sayHello();

    @RequestMapping(method = RequestMethod.GET, value = "/tmhello")
    String toHello();
}
