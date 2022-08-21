package com.devlhse.wiremockcep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WiremockCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiremockCepApplication.class, args);
	}

}
