package com.example;

import com.example.config.property.ApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class SpringBootIntro {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntro.class, args);
	}
}
