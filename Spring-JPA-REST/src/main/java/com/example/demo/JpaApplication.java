package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(JpaApplication.class, args);
		for (String name : ctx.getBeanDefinitionNames()){
			System.out.println(name);
		}
		System.out.println("******* Bean Count *******");
		System.out.println(ctx.getBeanDefinitionCount());
	}
}
