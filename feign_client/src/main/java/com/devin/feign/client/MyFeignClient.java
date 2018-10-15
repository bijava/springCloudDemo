package com.devin.feign.client;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import feign.Client;
import feign.Request;
import feign.Request.Options;
import feign.Response;

/**
 * feign.Client接口的实现类
 *
 * @author Devin
 *
 */
public class MyFeignClient implements Client {

    /**
     * 客户端实现过程：
     *
     * 1.   在实现execute方法时，将Feign的Request实例，
     *      转化为HttpClient的HttpRequestBase，
     *
     * 2.   再使用CloseableHttpClient来执行请求
     *
     * 3.   得到响应HttpResponse实例后，
     *      再转换为Feign的Response实例返回
     */
    public Response execute(Request request, Options options)
            throws IOException {

        System.out.println("   --->>> 这是自定义的Feign客户端   <<<---   ");

        try {
            // 创建一个默认的客户端
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 获取调用的HTTP方法
            final String method = request.method();

            // 创建一个HttpClient的HttpRequest
            HttpRequestBase httpRequestBase = new HttpRequestBase() {
                @Override
                public String getMethod() {
                    return method;
                }
            };

            // 设置请求地址
            httpRequestBase.setURI(new URI(request.url()));

            // 执行请求，获取响应
            HttpResponse httpResponse = httpClient.execute(httpRequestBase);

            // 获取响应的主体内容
            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());

            // 将HttpClient的响应对象转化为Feign的Response
            Response response = Response.builder().body(body)
                    .headers(new HashMap<String, Collection<String>>())
                    .status(httpResponse.getStatusLine().getStatusCode())
                    .build();

            return response;

        } catch (Exception e) {
            throw new IOException(e);
        }

    }

}
