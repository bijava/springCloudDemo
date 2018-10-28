package com.devin.feign;

import com.devin.feign.client.HelloClient;
import com.devin.feign.client.MyFeignClient;
import feign.Feign;
import feign.gson.GsonEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FeignClientMain {

    public static void main(String[] args) throws Exception {

        // 获取服务接口
//        HelloClient helloClient = Feign.builder().encoder(new GsonEncoder())
//                .client(new MyFeignClient())
//                .target(HelloClient.class, "http://localhost:8080");
//
//        // 请求Hello world接口
//        String result = helloClient.sayHello();
//
//        System.out.println("接口响应内容：" + result);

        CloseableHttpClient client = HttpClients.createDefault();
        // 发送post请求
        HttpPost post = new HttpPost("http://localhost:10000/actuator/bus-refresh");
        HttpResponse response = client.execute(post);
//        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
