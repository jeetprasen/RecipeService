package com.recipes.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.recipes.demo.security.AppProperties;

@SpringBootApplication
public class RecipeServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RecipeServiceApplication.class, args);
		System.out.println("Recipe-Service-Started!!");
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RecipeServiceApplication.class);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Bean("AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}

	/*
	 * @Bean public CorsFilter corsFilter() { final UrlBasedCorsConfigurationSource
	 * source = new UrlBasedCorsConfigurationSource();
	 * 
	 * List<String> allowedOrigins = new ArrayList<String>();
	 * allowedOrigins.add("http://localhost:4200");
	 * 
	 * final CorsConfiguration config = new CorsConfiguration();
	 * config.setAllowCredentials(true); config.setAllowedOrigins(allowedOrigins);
	 * config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
	 * config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
	 * "OPTIONS"));
	 * 
	 * source.registerCorsConfiguration("/**", config); return new
	 * CorsFilter(source); }
	 */

}
