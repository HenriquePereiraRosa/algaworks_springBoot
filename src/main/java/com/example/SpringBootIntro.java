package com.example;

import com.example.config.property.ApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableOAuth2Client
@EnableConfigurationProperties(ApiProperty.class)
public class SpringBootIntro {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntro.class, args);
	}
}
