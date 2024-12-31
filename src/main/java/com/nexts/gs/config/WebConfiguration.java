package com.nexts.gs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
    registry.addMapping("/**") // Allow CORS for all paths
        .allowedOrigins("*") // Allow requests from any origin
        .allowedMethods("*") // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
        .allowedHeaders("*") // Allow all headers
        .allowCredentials(false); // Do not allow cookies or authentication headers
  }
}
