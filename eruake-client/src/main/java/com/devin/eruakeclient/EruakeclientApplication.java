package com.devin.eruakeclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EruakeclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EruakeclientApplication.class, args);
	}
}
