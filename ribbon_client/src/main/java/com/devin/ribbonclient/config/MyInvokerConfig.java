package com.devin.ribbonclient.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 1.使Spring容器得知我们自定义的配置类
 * 2.name="atm-eureka-ribbon-provider",建议调用那个服务就配置那个服务
 * 3.当调用"atm-eureka-ribbon-provider"，这个服务的时候，将使用这个配置
 *
 * @author devin
 *
 */
@RibbonClient(name = " user-service", configuration = MyConfig.class)
public class MyInvokerConfig {

}
