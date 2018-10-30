package com.coviam.finance.backend.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.coviam.finance")
public class FinanceBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(FinanceBackendApplication.class, args);
  }


}

