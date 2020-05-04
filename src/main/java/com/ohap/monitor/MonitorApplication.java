package com.ohap.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
public class MonitorApplication {

	public static void main(String[] args) {
//		final SpringApplication application = new SpringApplication(MonitorApplication.class);
//		application.setWebApplicationType(WebApplicationType.REACTIVE);
//		application.run(args);
		SpringApplication.run(MonitorApplication.class, args);
	}

}
