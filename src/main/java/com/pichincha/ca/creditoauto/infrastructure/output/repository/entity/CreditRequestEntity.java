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
 * @class_name CreditRequestEntity.java
 * @class_description Class to persist credit requests in the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Entity
@Table(name = "credit_requests")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreditRequestEntity {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "car_id", nullable = false)
  private CarEntity car;

  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private ClientEntity client;

  @ManyToOne
  @JoinColumn(name = "seller_id", nullable = false)
  private SellerEntity seller;

  @ManyToOne
  @JoinColumn(name = "car_yard_id", nullable = false)
  private CarYardEntity carYard;

  @Column(name = "installments", nullable = false)
  private Integer installments;

  @Column(name = "month_terms", nullable = false)
  private Integer monthTerms;

  @Column(name = "observation", length = 512)
  private String observation;

  @Column(name = "upfront_payment", nullable = false)
  private Double upfrontPayment;

  @Column(name = "creation_date", nullable = false, columnDefinition = "timestamp")
  private ZonedDateTime creationDate;

  @Column(name = "status", nullable = false, length = 50)
  private String status;
}
