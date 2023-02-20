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


@SpringBootApplication
public class HelloworldApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelloworldApplication.class, args);
  }
  
  @Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("https://frontend-reactjs.web.app");
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("OPTIONS");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
  
}


@Component
@Order(0)
class AppCdsApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
  private final boolean appcds;
  private final ApplicationContext ctx;
  private final RestTemplate restTemplate = new RestTemplate();

  AppCdsApplicationListener(@Value("${appcds:false}") boolean appcds,
      ApplicationContext ctx) {
    this.appcds = appcds;
    this.ctx = ctx;
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    if (appcds) {
      restTemplate.getForEntity("http://localhost:8080/", String.class);
      SpringApplication.exit(ctx, () -> 0);
    }
  }
}

@RestController
class HelloController {
  @GetMapping("/")
  String hello() {
    return "Hello World! Welcome to TEST API Services11111...";
  }
}


@RestController
@RequestMapping("/api/v1")
class HelloControllerV1 {

    @GetMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        String message = "Hello, " + name + "!";
        return "{\"message\": \"" + message + "\"}";
    }
}

@RestController
@RequestMapping("/api/v2")
class HelloControllerV2 {

    @GetMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Welcome " + name;
    }
}
