package com.pichincha.ca.creditoauto.domain.enums;

import static java.util.Arrays.stream;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jocamach@pichincha.com
 * @class_name MaritalStatus.java
 * @class_description Contains an enumerator for marital status
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public enum MaritalStatusEnum {
  MARRIED,
  SINGLE,
  DIVORCED,
  WIDOWED;

  private static final Map<String, MaritalStatusEnum> VALUES = new HashMap<>();

  static {
    stream(values()).forEach(value -> VALUES.put(value.name(), value));
  }

  public static MaritalStatusEnum of(String value) {
    return VALUES.get(value);
  }
}
