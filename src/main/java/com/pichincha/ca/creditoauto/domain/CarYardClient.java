package com.pichincha.ca.creditoauto.domain;

import java.time.ZonedDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardClient.java
 * @class_description Contains the relation between car yards and clients
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarYardClient {

  private Long id;

  @EqualsAndHashCode.Include
  private CarYard carYard;

  @EqualsAndHashCode.Include
  private Client client;

  private ZonedDateTime assignedDate;
}
