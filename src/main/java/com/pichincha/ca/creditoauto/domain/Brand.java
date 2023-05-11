package com.pichincha.ca.creditoauto.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name Brand.java
 * @class_description Contains brand information associated with cars
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Brand {

  private Long id;

  @EqualsAndHashCode.Include
  private String name;
}
