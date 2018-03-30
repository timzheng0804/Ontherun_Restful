package com.ontherun.restful_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class RestfulApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulApiApplication.class, args);
	}

}