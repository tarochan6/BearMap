package com.example.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.example.app.mapper")
@SpringBootApplication
public class BearMapApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BearMapApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(
	SpringApplicationBuilder application) {
	return application.sources(BearMapApplication.class);
	}

}
