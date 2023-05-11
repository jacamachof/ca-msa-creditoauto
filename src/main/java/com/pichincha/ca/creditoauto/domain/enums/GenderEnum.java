package com.pichincha.ca.creditoauto.domain.enums;

import static java.util.Arrays.stream;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jocamach@pichincha.com
 * @class_name Gender.java
 * @class_description Contains an enumeration for gender
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public enum GenderEnum {
  MALE,
  FEMALE,
  NON_BINARY;

  private static final Map<String, GenderEnum> VALUES = new HashMap<>();

  static {
    stream(values()).forEach(value -> VALUES.put(value.name(), value));
  }

  public static GenderEnum of(String value) {
    return VALUES.get(value);
  }
}
