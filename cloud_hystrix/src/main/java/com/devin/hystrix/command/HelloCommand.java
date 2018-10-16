package com.devin.hystrix.command;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * 设置命令组的key，默认用来作为线程池的key
 * @author devin
 */
public class HelloCommand extends HystrixCommand<String> {
    private String url;

    CloseableHttpClient httpClient;

    public HelloCommand(String url) {
        // 调用父类的构造器，设置命令组的key，默认用来作为线程池的key
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        // 创建HttpClient客户端
        this.httpClient = HttpClients.createDefault();
        this.url = url;
    }

    @Override
    protected String run() throws Exception {
        try {
            // 调用GET方法请求服务
            HttpGet httpGet = new HttpGet(url);
            // 得到服务响应
            HttpResponse response = httpClient.execute(httpGet);
            // 解析并返回命令执行结果
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getFallback() {
        System.out.println("执行 HelloCommand 的回退方法");
        return "error";
    }

}
