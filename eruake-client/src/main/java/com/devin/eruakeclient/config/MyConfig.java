package com.devin.eruakeclient.config;

import com.devin.eruakeclient.entity.Person;
import com.devin.eruakeclient.rule.MyRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 配置自定义规则
 * @author devin
 *
 */
@Configuration
//@RefreshScope
public class MyConfig {

//    @Bean
//    public IRule getRule(){
//        return new MyRule();
//    }

    /**
     * 刷新Bean的示例
     * @param env
     * @return
     */
    @Bean
    @RefreshScope
    public Person person(Environment env) {
        // 读取名字创建Person
        String name = env.getProperty("test.user.name");
        // 输出Person名字
        System.out.println("初始化Person bean：" + name);
        // 创建一个Bean
        Person p = new Person();
        p.setName(name);
        System.out.println(p.toString());
        return p;
    }
}
