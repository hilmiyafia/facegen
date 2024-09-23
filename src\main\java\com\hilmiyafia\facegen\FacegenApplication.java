package com.hilmiyafia.facegen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FacegenApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FacegenApplication.class, args);
	}
}
