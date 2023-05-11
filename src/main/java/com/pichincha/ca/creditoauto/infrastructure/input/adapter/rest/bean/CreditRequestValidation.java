package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.bean;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestValidation.java
 * @class_description Annotation to validate the incoming credit request status values
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CreditRequestValidator.class)
public @interface CreditRequestValidation {

  String message() default "{creditRequestStatus.not-valid}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}