package com.pichincha.ca.creditoauto.domain;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYard.java
 * @class_description Contains car yard information
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarYard {

  @EqualsAndHashCode.Include
  private Long id;

  private String name;
  private String address;
  private String phoneNumber;
  private String pointOfSale;

  @ToString.Exclude
  private List<Client> clients;
  @ToString.Exclude
  private List<Seller> sellers;
}
