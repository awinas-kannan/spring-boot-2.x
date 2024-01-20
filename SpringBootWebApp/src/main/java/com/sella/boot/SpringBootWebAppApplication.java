package com.sella.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
/* @EnableSwagger2 */
public class SpringBootWebAppApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringBootWebAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebAppApplication.class, args);
		System.out.println("Working..");
		logger.info("logged");

	}

	
	//Resttemplate bean will be created,we can use it using AutoWired annotation
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/*
	 * Access to XMLHttpRequest at 'http://localhost:9090/saveEmployee' from origin
	 * 'http://localhost:8080' has been blocked by CORS policy: Response to
	 * preflight request doesn't pass access control check: It does not have HTTP ok
	 * status.
	 */
	
	//at the time of wrking with this, the appication ran on 9090 and 
	//the applicaion which tried to access ran on 8080
	
	// 3000 - react
	
	@SuppressWarnings("deprecation")
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/saveEmployee").allowedOrigins("http://localhost:8080");
				
				//React Applicaiton get request
				registry.addMapping("/getAllEmployee").allowedOrigins("http://localhost:3000");
				registry.addMapping("/saveEmployee").allowedOrigins("http://localhost:3000");
				registry.addMapping("/Employee/*").allowedOrigins("http://localhost:3000");
				registry.addMapping("/getAllLaptops").allowedOrigins("http://localhost:3000");
				registry.addMapping("/searchEmployeeByName").allowedOrigins("http://localhost:3000");
				
			}
		};
	}
	/*
	 * @Bean public Docket productApi() { return new
	 * Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.basePackage("com.sella.boot")).build(); }
	 */
}
