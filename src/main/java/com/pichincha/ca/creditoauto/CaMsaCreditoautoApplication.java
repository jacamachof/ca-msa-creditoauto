package com.pichincha.ca.creditoauto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class CaMsaCreditoautoApplication {

  public static void main(String[] args) {
    SpringApplication.run(CaMsaCreditoautoApplication.class, args);
  }
}
