package com.devin.cxfclient.client;

import java.util.HashMap;
import java.util.Map;

import org.restlet.data.MediaType;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

public class MyRestletClient {

    public static void main(String[] args) throws Exception {

        ClientResource clientResource = new ClientResource(
                "http://localhost:8080/student/2");

        // 调用GET方法，服务端发布的是GET
        Representation representation = clientResource
                .get(MediaType.APPLICATION_JSON);

        // 创建JacksonRepresentation实例，将响应转化为Map
        JacksonRepresentation jacksonRepresentation = new JacksonRepresentation(
                representation, HashMap.class);

        // 获取转化后的Map对象
        Map map = (HashMap) jacksonRepresentation.getObject();

        // 输出结果
        System.out.println(map.get("no") + "--" + map.get("name") + "--"
                + map.get("age"));
    }
}
