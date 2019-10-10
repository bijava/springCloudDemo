package com.devin.ribbonclient.client;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.http.RestClient;

public class MyRESTClient {

    public static void main(String[] args) {

        // 设置请求的服务器
        ConfigurationManager.getConfigInstance().setProperty(
                "user-service.ribbon.listOfServers",
                "localhost:8080,localhost:8081");

        // 获取REST请求客户端
        RestClient client = (RestClient) ClientFactory
                .getNamedClient("user-service");

        // 创建请求实例
        HttpRequest request = HttpRequest.newBuilder().uri("/student/1002").build();

        //发送6次请求到服务器中
        for (int i = 0; i < 6; i++) {
            try {

                HttpResponse response = client.executeWithLoadBalancer(request);
//                String result = response.getEntity(String.class);

                System.out.println(response.getRequestedURI());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}