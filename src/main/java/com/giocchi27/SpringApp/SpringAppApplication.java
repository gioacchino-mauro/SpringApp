package com.giocchi27.SpringApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
		System.out.println("RequestMapping = (\"api/v1\")");
	}

}
