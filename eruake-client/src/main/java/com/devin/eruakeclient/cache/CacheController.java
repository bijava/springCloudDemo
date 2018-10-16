package com.devin.eruakeclient.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存机制，调用服务，缓存的时服务调用
 */
@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/cacheRouter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String cacheRouter() {
        StringBuffer cacheData = new StringBuffer();

        for (int i = 0; i < 5; i++) {
            cacheService.cachePerson(1);
        }
        return "访问成功";
    }

    @RequestMapping(value = "/rmCache", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String rmCache() {

        cacheService.getCache(1);
        // 形式上调用了两次，实际上，当一次调用成功，第二次不再调用，因为已经存在缓存
        cacheService.getCache(1);

        // 删除缓存
        cacheService.removePerson(1);
        System.out.println("================");
        // 缓存如果删除成功，则会再次请求方法
        cacheService.getCache(1);

        return "访问成功";
    }
}
