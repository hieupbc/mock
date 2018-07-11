package com.example.mockbus.JwtConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {

    registry.addMapping("/**")
        .allowedOrigins("http://localhost:4200")
        .allowedMethods("POST", "GET", "PUT", "DELETE","OPTIONS")
//        .allowedHeaders("Content-Type")
//        .allowedHeaders("Access-Control-Allow-Origin")
//        .allowedHeaders("Access-Control-Allow-Headers","Authorization")
        .exposedHeaders("header-1", "header-2")
        .allowCredentials(false)
        .maxAge(6000);

  }
}