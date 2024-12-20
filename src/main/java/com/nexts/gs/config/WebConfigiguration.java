package com.nexts.gs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigiguration {
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**") // Match all endpoints
            .allowedOrigins("*") // Allow all origins
            .allowedMethods("*") // Allow all HTTP methods
            .allowedHeaders("*") // Allow all headers
            .allowCredentials(false); // Disable credentials if not needed
      }
    };
  }
}
