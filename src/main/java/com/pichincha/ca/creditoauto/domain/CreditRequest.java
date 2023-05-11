package com.pichincha.ca.creditoauto.domain;

import com.pichincha.ca.creditoauto.domain.enums.CreditRequestEnum;
import java.time.ZonedDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequest.java
 * @class_description Contains credit request information associated with a car payment, client, car
 * yard and seller
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreditRequest {

  @EqualsAndHashCode.Include
  private Long id;

  private Car car;
  private Client client;
  private Seller seller;
  private CarYard carYard;

  private Integer installments;
  private Integer monthTerms;
  private String observation;
  private Double upfrontPayment;
  private ZonedDateTime creationDate;

  private CreditRequestEnum status;
}
