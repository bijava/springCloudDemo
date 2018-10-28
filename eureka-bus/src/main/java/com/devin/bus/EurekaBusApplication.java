package com.devin.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaBusApplication.class, args);
	}
}
