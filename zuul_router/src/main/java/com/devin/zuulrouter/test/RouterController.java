package com.devin.zuulrouter.test;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Router测试控制器
@RestController
public class RouterController {

    @RequestMapping(value = "/receive/{personId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String receive(@PathVariable("personId") Integer personId) {
        return "Hello Receive!" + personId;
    }
}
