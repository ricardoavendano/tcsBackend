package com.tcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan 
@SpringBootApplication
public class TcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcsApplication.class, args);
	}

}
