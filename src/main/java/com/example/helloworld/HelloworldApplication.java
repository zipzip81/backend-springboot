package com.example.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
@RestController
public class HelloworldApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelloworldApplication.class, args);
  }
	
 @GetMapping("/")
 public String hello() {
        return "Hello World! Welcome to TEST API Services...";
 }
	
 @GetMapping("/api/v1/hello/{name}")
 public String helloV1(@PathVariable String name) {
        String message = "Hello, " + name + "!";
        return "{\"message\": \"" + message + "\"}";
 }
    
 @GetMapping("/api/v2/hello/{name}")
 public String helloV2(@PathVariable String name) {
        return "Hello " + name + " from API v2!";
 }
    
 @Bean
 public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
 }
	
}
