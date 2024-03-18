package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing //BaseEntity 참고 : 자동감지 리스너를 작동시켜줌
public class ChildProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChildProjectApplication.class, args);
	}

}