package com.pragma.mealssquare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MealssquareApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealssquareApplication.class, args);
	}

}
