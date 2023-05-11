package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.bean;

import com.pichincha.ca.creditoauto.domain.enums.CreditRequestEnum;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestValidator.java
 * @class_description Implements the CreditRequestValidation annotation
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class CreditRequestValidator implements
    ConstraintValidator<CreditRequestValidation, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (Objects.isNull(value)) {
      return true;
    }

    return Objects.nonNull(CreditRequestEnum.of(value));
  }
}
