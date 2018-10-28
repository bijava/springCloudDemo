package com.devin.context.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于获取Spring Context
 */
@RestController
public class SpringContextController {

    /**
     * 1.引导程序上下文的父上下文（父上下文先创建）会默认读取src/main/resouces中的bootstrap.yml
     * 2.如果application.yml与bootstrap.yml中属性相同，则读取的是application.yml中的属性
     * 3.配置客户端引导程序在创建上下文的时候，会读取远程配置，查看启动日志 ：
     *  2018-03-22 14:31:18.873 INFO 2816 [ restartedMain] c.c.c.ConfigServicePropertySourceLocator :
     *  Fetching config from server at: http://localhost:8888
     *  会去8888端口抓取配置，也就是说引导程序在创建的时候会去远程抓取配置，使用抓取的配置来启动自己的上下文
     */

    // ApplicationContext为该程序的上下文
    // 父上下文默认会读取src/main/resouces中的bootstrap.yml
    @Autowired
    private ApplicationContext ctx;

    @GetMapping("/getSCtx")
    @ResponseBody
    public String getSpringContext() {
        System.out.println("-------");
        System.out.println(ctx);
        // 在我们的容器之上，还有一个父容器，父容器会加载bootstrap.yml的配置文件
        System.out.println(ctx.getParent());

        // 父上下文创建的时候，就会去读取bootstrap.yml
        // 如果在application.yml中有同样的属性，则会被覆盖
        Environment env = ctx.getEnvironment();
        System.out.println(env.getProperty("test.user.name"));
        return "Success!";
    }
}