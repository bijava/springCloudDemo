package com.devin.feign;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class EncryptMain {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        // 发送post请求
        HttpPost post = new HttpPost("http://localhost:8888/encrypt");
//        HttpPost post = new HttpPost("http://localhost:8888/decrypt");

        // 设置请求的参数，对20180323进行加密，编码格式为UTF-8
        HttpEntity entity = new StringEntity("devinpwd", Consts.UTF_8);
        // 设置请求的参数，对2cdf324e7d8c6271d883a7a9bdcac532d027141545f1fed273f8c2b803bc3e9d进行解密，编码格式为UTF-8
//        HttpEntity entity = new StringEntity("71efed7d9fd3a704524bb46091e1232b0d9295e2057b7ab5d8a26a29c68391e3", Consts.UTF_8);
        post.setEntity(entity);

        HttpResponse response = client.execute(post);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
