package com.pichincha.ca.creditoauto.infrastructure.output.repository.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name CarEntity.java
 * @class_description Class to persist vehicles in the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Entity
@Table(name = "cars")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @EqualsAndHashCode.Include
  @Column(name = "plate", nullable = false, unique = true)
  private String plate;

  @Column(name = "model", nullable = false)
  private String model;

  @Column(name = "vin", nullable = false, length = 20)
  private String vin;

  @ManyToOne
  @JoinColumn(name = "brand_id", nullable = false)
  private BrandEntity brand;

  @Column(name = "type")
  private String type;

  @Column(name = "cylinder_capacity", nullable = false)
  private Double cylinderCapacity;

  @Column(name = "value", nullable = false)
  private Double value;

  @ToString.Exclude
  @OneToMany(mappedBy = "car")
  @OrderBy("creationDate DESC")
  private List<CreditRequestEntity> creditRequests;
}
