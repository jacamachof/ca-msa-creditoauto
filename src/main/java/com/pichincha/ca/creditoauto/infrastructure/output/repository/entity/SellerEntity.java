package com.pichincha.ca.creditoauto.infrastructure.output.repository.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name SellerEntity.java
 * @class_description Class to persist sellers in the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Entity
@Table(name = "sellers")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class SellerEntity extends AbstractPerson {

  @ManyToOne
  @JoinColumn(name = "car_yard_id")
  private CarYardEntity carYard;
}
