package com.devin.load.banlanced.interceptor;

import java.io.IOException;

import com.devin.load.banlanced.http.MyRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class MyInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        System.out.println("   --->>> 这是自定义拦截器   <<<---   ");
        System.out.println("   --->>> 原来的URI：" + request.getURI());

        //实现负载均衡功能，将原来请求封装为新的请求
        MyRequest newRequest = new MyRequest(request);

        System.out.println("   --->>> 拦截后新的URI："+newRequest.getURI());

        return execution.execute(newRequest, body);
    }

}
