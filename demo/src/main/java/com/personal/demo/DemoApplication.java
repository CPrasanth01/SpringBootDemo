package com.personal.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.personal.demo.domain.Demo;
import com.personal.demo.repository.DemoRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner AccessH2(DemoRepository demo) {
		return (args) -> {
			demo.save(new Demo("Hi ","A"));
			demo.save(new Demo("Say ","B"));
		};
	}
}
