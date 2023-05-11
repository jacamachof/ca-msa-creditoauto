package com.pichincha.ca.creditoauto.infrastructure.output.repository.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardEntity.java
 * @class_description Class to persist card yards in the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Entity
@Table(name = "car_yards")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarYardEntity {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "address", nullable = false, length = 512)
  private String address;

  @Column(name = "phone_number", nullable = false, length = 20)
  private String phoneNumber;

  @Column(name = "point_of_sale", nullable = false)
  private String pointOfSale;

  @ToString.Exclude
  @OneToMany(mappedBy = "carYard")
  private List<ClientEntity> clients;

  @ToString.Exclude
  @OneToMany(mappedBy = "carYard")
  private List<SellerEntity> sellers;
}
