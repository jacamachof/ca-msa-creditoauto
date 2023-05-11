package com.pichincha.ca.creditoauto.domain.enums;

import static java.util.Arrays.stream;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestStatus.java
 * @class_description Contains the credit request statuses
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public enum CreditRequestEnum {
  REGISTERED,
  DISPATCHED,
  CANCELED;

  private static final Map<String, CreditRequestEnum> VALUES = new HashMap<>();

  static {
    stream(values()).forEach(value -> VALUES.put(value.name(), value));
  }

  public static CreditRequestEnum of(String value) {
    return VALUES.get(value);
  }
}
