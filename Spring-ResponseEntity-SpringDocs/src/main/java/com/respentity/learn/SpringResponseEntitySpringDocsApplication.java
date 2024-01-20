package com.respentity.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
public class SpringResponseEntitySpringDocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringResponseEntitySpringDocsApplication.class, args);
	}

}

@OpenAPIDefinition(servers = {
		@Server(url = "https://stg.app.com/", description = "STAGE"),
		@Server(url = "https://dev.app.com/", description = "DEV"),
		@Server(url = "http://localhost:8080/", description = "Local") })
class OpenApiConfig {
}