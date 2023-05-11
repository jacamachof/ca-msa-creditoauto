package com.pichincha.ca.creditoauto.infrastructure.config;

import java.util.ResourceBundle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jocamach@pichincha.com
 * @class_name ResourceBundleConfig.java
 * @class_description Configures the ResourceBundle bean to load error messages from
 * classpath:messages.properties and append these to the HTTP error codes
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Configuration
public class ResourceBundleConfig {

  @Bean
  public ResourceBundle resourceBundle() {
    return ResourceBundle.getBundle("messages");
  }
}
