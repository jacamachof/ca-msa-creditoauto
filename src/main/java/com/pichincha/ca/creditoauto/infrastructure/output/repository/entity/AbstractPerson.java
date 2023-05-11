package com.pichincha.ca.creditoauto.infrastructure.output.repository.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name AbstractPerson.java
 * @class_description Abstract class to persist basic person information for Clients and Sellers
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@MappedSuperclass
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AbstractPerson {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @EqualsAndHashCode.Include
  @Column(name = "identification", nullable = false, unique = true, length = 20)
  private String identification;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "address", nullable = false, length = 512)
  private String address;

  @Column(name = "local_phone_number", nullable = false, length = 20)
  private String localPhoneNumber;

  @Column(name = "mobile_phone_number", nullable = false, length = 20)
  private String mobilePhoneNumber;

  @Column(name = "birthdate", nullable = false, columnDefinition = "date")
  private LocalDate birthdate;

  @Column(name = "gender", nullable = false, length = 50)
  private String gender;

  @Column(name = "marital_status", nullable = false, length = 50)
  private String maritalStatus;
}
