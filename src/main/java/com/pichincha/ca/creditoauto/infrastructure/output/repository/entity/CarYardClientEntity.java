package com.pichincha.ca.creditoauto.infrastructure.output.repository.entity;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardEntity.java
 * @class_description Class to persist the relation between car yards and clients
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Entity
@Table(name = "car_yard_clients")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarYardClientEntity {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "car_yard_id", nullable = false)
  private CarYardEntity carYard;

  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private ClientEntity client;

  @Column(name = "assigned_date", nullable = false, columnDefinition = "timestamp")
  private ZonedDateTime assignedDate;
}
