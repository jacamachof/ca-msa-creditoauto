package com.pichincha.ca.creditoauto.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name Seller.java
 * @class_description Contains seller information
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Seller extends Person {

  private CarYard carYard;
}
