package com.pichincha.ca.creditoauto.infrastructure.output.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name BrandEntity.java
 * @class_description Class to persist car brands for vehicles
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Entity
@Table(name = "brands")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BrandEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @EqualsAndHashCode.Include
  @Column(name = "name", nullable = false, unique = true)
  private String name;
}
