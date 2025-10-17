package com.example.balkan_cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BalkanCarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalkanCarsApplication.class, args);
	}

}
