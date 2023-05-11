package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.bean.CreditRequestValidation;
import java.time.ZonedDateTime;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestDto.java
 * @class_description Contains credit request information associated with a car payment, client, car
 * yard and seller
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequestDto {

  @Min(1)
  private Long id;

  @NotNull
  private CarDto car;

  @NotNull
  private ClientDto client;

  @NotNull
  private SellerDto seller;

  @NotNull
  private CarYardDto carYard;

  @NotNull
  @Min(1)
  private Integer installments;

  @NotNull
  @Min(1)
  private Integer monthTerms;

  private String observation;

  @NotNull
  @DecimalMax(value = "100000000.00")
  @DecimalMin(value = "000000001.00")
  private Double upfrontPayment;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  private ZonedDateTime creationDate;

  @NotNull
  @CreditRequestValidation()
  private String status;
}
