package com.stranger.gas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GasApplication {
	public static void main(String[] args) {
		SpringApplication.run(GasApplication.class, args);
	}
}
