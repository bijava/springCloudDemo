package com.devin.eruakeclient.service;

import com.devin.eruakeclient.entity.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
// @DefaultProperties(defaultFallback = "findPersonFallback") //全局配置
public class PersonService {

    @Autowired
    RestTemplate restTemplate;

    //使用Hystrix的注解，当服务提供者不启动时，直接直接回退方法
//    @HystrixCommand(fallbackMethod = "findPersonFallback")
    /**
     *  commandKey：之前用作缓存 ，
     *  groupKey：用于执行线程，
     *  重要的是超时时间的配置，
     *  线程池的配置
     */
    @HystrixCommand(
            fallbackMethod = "findPersonFallback",
            groupKey = "PersonGroupKey",
            commandKey = "PersonCommandKey",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2")
            })
    public Person findPerson(Integer personId) {
        Person person = restTemplate.getForObject(
                "http://user-service/person/{personId}",
                Person.class, personId);
        return person;
    }

    // fallBack方法需要和findPerson的参数一致
    public Person findPersonFallback(Integer personId) {
        Person p = new Person();

        p.setId(10);
        p.setAge(personId);
        p.setName("i am fallback!");

        return p;
    }

    // 默认的回退方法，是没有参数的  (全局配置时使用)
//    public Person findPersonFallback() {
//        Person p = new Person();
//
//        p.setId(10);
//        p.setAge(1);
//        p.setName("i am fallback!");
//
//        return p;
//    }
}
