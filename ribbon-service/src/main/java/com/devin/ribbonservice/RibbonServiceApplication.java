package com.devin.ribbonservice;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class RibbonServiceApplication {

	public static void main(String[] args) {
//		SpringApplication.run(RibbonServiceApplication.class, args);
		new SpringApplicationBuilder(RibbonServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
	}
}
