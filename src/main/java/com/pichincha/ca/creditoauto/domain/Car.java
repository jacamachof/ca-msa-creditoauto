package com.pichincha.ca.creditoauto.domain;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name Car.java
 * @class_description Contains car information for selling on car yards
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Car {

  private Long id;

  @EqualsAndHashCode.Include
  private String plate;

  private String model;
  private String vin;
  private Brand brand;
  private String type;
  private Double cylinderCapacity;
  private Double value;

  @ToString.Exclude
  private List<CreditRequest> creditRequests;
}
