package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.bean;

import com.pichincha.ca.creditoauto.domain.enums.MaritalStatusEnum;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author jocamach@pichincha.com
 * @class_name MaritalStatusValidator.java
 * @class_description Implements the MaritalStatusValidation annotation
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class MaritalStatusValidator implements
    ConstraintValidator<MaritalStatusValidation, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (Objects.isNull(value)) {
      return true;
    }

    return Objects.nonNull(MaritalStatusEnum.of(value));
  }
}
