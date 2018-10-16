package com.devin.eruakeserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Scanner;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

	public static void main(String[] args) {
		//读取控制台输入，决定使用哪个profiles
		System.out.print("服务启动>>>>>>\n\t请输入启动的port(slave1/slave2):");
		Scanner scanner=new Scanner(System.in);

		String profiles=scanner.nextLine();

		// SpringApplication.run(EurekaServerApplication.class, args);
		new SpringApplicationBuilder(EurekaServerApplication.class).profiles(profiles).run(args);
	}
}
