package com.devin.cxfclient.client;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

public class MyCxfClient {

    public static void main(String[] args) throws IOException {

        //创建WebClient
        WebClient webClient = WebClient.create("http://localhost:8080/student/2");

        //获取响应
        Response response=webClient.get();

        //获取响应内容
        InputStream inputStream=(InputStream) response.getEntity();
        String contStr=IOUtils.readStringFromStream(inputStream);

        //输出字符串
        System.out.println(contStr);
    }
}
