package com.devin.eruakeclient;

import com.devin.eruakeclient.service.SendMessageInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableEurekaClient
// 打开断路器
@EnableCircuitBreaker
// 扫描
@ServletComponentScan
//开启Feign
@EnableFeignClients
@EnableBinding(SendMessageInterface.class)//开启绑定
public class EruakeclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EruakeclientApplication.class, args);
	}
}
