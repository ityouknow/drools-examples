package com.hlover.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hlover
 */
@SpringBootApplication
@EnableScheduling
public class DroolsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroolsDemoApplication.class, args);
	}

}
