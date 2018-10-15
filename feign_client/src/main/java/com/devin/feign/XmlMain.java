package com.devin.feign;

import com.devin.feign.client.PersonClient;
import com.devin.feign.client.PersonClient.*;
import feign.Feign;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import feign.jaxb.JAXBEncoder;

public class XmlMain {
    public static void main(String[] args) {

        JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder()
                .build();
        // 获取服务接口
        PersonClient personClient = Feign.builder()
                .encoder(new JAXBEncoder(jaxbFactory))
                .decoder(new JAXBDecoder(jaxbFactory))
                .target(PersonClient.class, "http://localhost:8080/");

        // 构建参数
        Person person = new Person();

        person.setId(1002);
        person.setName("devin");
        person.setAge(18);
        // 调用接口并返回结果
        Result result = personClient.createPersonXML(person);
        System.out.println(result.getMessage());

    }
}
