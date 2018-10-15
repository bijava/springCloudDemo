package com.devin.feign;

import com.devin.feign.client.HelloClient;
import com.devin.feign.client.PersonClient;
import com.devin.feign.client.PersonClient.Person;
import com.devin.feign.client.StudentClient;
import com.devin.feign.client.StudentClient.Student;
import com.devin.feign.contract.MyContract;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class HelloMain {

    public static void main(String[] args) {

        // 调用Hello接口
        HelloClient helloClient = Feign.builder().target(HelloClient.class,
                "http://localhost:8080/");
        System.out.println(helloClient.sayHello());

        // 获取服务接口
//        HelloClient helloClient = Feign.builder().contract(new MyContract())
//                .target(HelloClient.class, "http://localhost:8080/");
//        // 请求 Hello World 接口
//        String result = helloClient.sayHello();
//        System.out.println(" 接口响应内容：" + result);

        System.out.println("\t>>>>>>>>>>>>>>>>>>");

        StudentClient studentClient = Feign.builder().decoder(new GsonDecoder())
                .target(StudentClient.class, "http://localhost:8080/");

        //对比可以发现
        //1.创建客户端
        //2.调用（甚至无需关心API地址）
        Student student = studentClient.findStudent(2001);

        System.out.println(student.getNo());
        System.out.println(student.getAddress());
        System.out.println(student.getName());

        System.out.println("\t>>>>>>>>>>>>>>>>>>");

        // 获取服务接口
        PersonClient personClient = Feign.builder().encoder(new GsonEncoder())
                .target(PersonClient.class, "http://localhost:8080/");

        // 创建参数的实例
        Person person = new Person();

        person.setId(1002);
        person.setName("devin");
        person.setAge(18);

        String reponseStr = personClient.createPerson(person);

        System.out.println(reponseStr);
        System.out.println("\t>>>>>>>>>>>>>>>>>>");
    }
}
