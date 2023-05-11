package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author jocamach@pichincha.com
 * @class_name ResourceBundleConfig.java
 * @class_description Configures the ResourceBundle bean to load error messages from
 * classpath:messages.properties and append these to the HTTP error codes
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Configuration
public class ValidatorConfig {

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public LocalValidatorFactoryBean getValidator() {
    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource());
    return bean;
  }
}
