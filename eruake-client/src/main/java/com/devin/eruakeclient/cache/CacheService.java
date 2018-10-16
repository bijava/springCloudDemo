package com.devin.eruakeclient.cache;

import com.devin.eruakeclient.entity.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CacheService {

    @Autowired
    RestTemplate restTemplate;

    // @CacheResult需要配合@HystrixCommand一起使用
    @CacheResult
    @HystrixCommand
    public Person cachePerson(Integer personId) {
        System.out.println("调用CachePerson()...");
        return null;
    }

    // 调用这个方法将设置缓存
    @CacheResult
    @HystrixCommand(commandKey = "cacheKey")
    public String getCache(Integer id) {
        System.out.println("查询缓存()....");
        return "";
    }

    // 调用这个方法将设清除缓存
    @CacheRemove(commandKey = "cacheKey")
    @HystrixCommand
    public String removePerson(Integer id) {
        System.out.println("删除()....");
        return "";
    }
}
