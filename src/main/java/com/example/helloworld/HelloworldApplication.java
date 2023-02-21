package com.example.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
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
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer {

   @Override
   public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
         .allowedOrigins("*")
         .allowedMethods("GET", "POST", "PUT", "DELETE")
         .allowedHeaders("*")
         .allowCredentials(false)
         .maxAge(3600);
   }
}

@SpringBootApplication
public class HelloworldApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelloworldApplication.class, args);
  }
}

@RestController
@CrossOrigin(origins = "*")
class HelloController {
  @GetMapping("/")
  String hello() {
    return "Hello World! Welcome to TEST API Services...";
  }
}


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
class HelloControllerV1 {

    @GetMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        String message = "Hello, " + name + "!";
        return "{\"message\": \"" + message + "\"}";
    }
}

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v2")
class HelloControllerV2 {

    @GetMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Welcome " + name;
    }
}
