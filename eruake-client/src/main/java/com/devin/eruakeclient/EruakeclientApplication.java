package com.devin.eruakeclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
// 打开断路器
@EnableCircuitBreaker
// 扫描
@ServletComponentScan
//打开Feign注解
@EnableFeignClients
public class EruakeclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EruakeclientApplication.class, args);
	}
}
