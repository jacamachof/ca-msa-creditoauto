package com.pichincha.ca.creditoauto.domain;

import java.time.ZonedDateTime;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name Client.java
 * @class_description Contains client information
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Client extends Person {

  private CarYard carYard;
  private Boolean creditApproval;
  private ZonedDateTime assignedDate;

  @ToString.Exclude
  private List<CreditRequest> creditRequests;
}
