package com.nav.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApiDataApplication  /* extends SpringBootServletInitializer*/ {

	
   /* @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CourseApiDataApplication.class);
    } */
    
	public static void main(String[] args) {
		SpringApplication.run(CourseApiDataApplication.class, args);
	}
}
