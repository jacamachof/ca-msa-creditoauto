package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.bean;

import com.pichincha.ca.creditoauto.domain.enums.GenderEnum;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author jocamach@pichincha.com
 * @class_name GenderValidator.java
 * @class_description Implements the GenderValidation annotation
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class GenderValidator implements ConstraintValidator<GenderValidation, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (Objects.isNull(value)) {
      return true;
    }

    return Objects.nonNull(GenderEnum.of(value));
  }
}
