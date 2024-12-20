package com.nexts.gs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "com.nexts.gs")
public class HuntingGameArAdminBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(HuntingGameArAdminBackendApplication.class, args);
  }

}
