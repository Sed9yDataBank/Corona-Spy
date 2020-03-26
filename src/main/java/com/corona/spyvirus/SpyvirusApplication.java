package com.corona.spyvirus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpyvirusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpyvirusApplication.class, args);
	}

}
