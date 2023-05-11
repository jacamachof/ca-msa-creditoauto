package com.pichincha.ca.creditoauto.domain;

import com.pichincha.ca.creditoauto.domain.enums.GenderEnum;
import com.pichincha.ca.creditoauto.domain.enums.MaritalStatusEnum;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name Person.java
 * @class_description Contains basic person information
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Person {

  private Long id;

  @EqualsAndHashCode.Include
  private String identification;

  private String firstName;
  private String lastName;
  private String address;
  private String localPhoneNumber;
  private String mobilePhoneNumber;
  private LocalDate birthdate;
  private GenderEnum gender;
  private MaritalStatusEnum maritalStatus;
}
