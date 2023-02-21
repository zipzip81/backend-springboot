package com.example.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

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
	
  @RequestMapping("/api/v1")
  @GetMapping("/hello/{name}")
  public String helloV1(@PathVariable String name) {
        String message = "Hello, " + name + "!";
        return "{\"message\": \"" + message + "\"}";
  }

  @RequestMapping("/api/v2")
  @GetMapping("/hello/{name}")
  public String helloV2(@PathVariable String name) {
        return "Welcome " + name;
  }
	
}
