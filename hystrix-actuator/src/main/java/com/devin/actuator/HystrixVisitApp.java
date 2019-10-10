package com.devin.actuator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixVisitApp {

	public static void main(String[] args) {
		new SpringApplicationBuilder(HystrixVisitApp.class).properties(
				"server.port=8083").run(args);
	}

}
