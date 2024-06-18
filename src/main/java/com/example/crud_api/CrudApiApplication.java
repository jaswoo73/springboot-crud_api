package com.example.crud_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CrudApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrudApiApplication.class, args);
		System.out.println("Hello World");
	}
}
