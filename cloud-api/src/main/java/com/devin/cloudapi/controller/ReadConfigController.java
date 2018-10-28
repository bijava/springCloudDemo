package com.devin.cloudapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取远程的配置
 */
@RestController
@RefreshScope
public class ReadConfigController {

    @Autowired
    private Environment environment;

    @GetMapping("/readConfig")
    public String readConfig() {
        return environment.getProperty("test.user.name");
    }
}
