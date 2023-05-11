package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto;

import java.util.List;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name CarDto.java
 * @class_description Contains car information for selling on car yards
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

  @Min(1)
  private Long id;

  @NotBlank
  @Size(min = 1, max = 255)
  private String plate;

  @NotBlank
  @Size(min = 1, max = 255)
  private String model;

  @NotBlank
  @Size(min = 1, max = 20)
  private String vin;

  @NotNull
  private BrandDto brand;

  private String type;

  @NotNull
  @DecimalMax(value = "100.00")
  @DecimalMin(value = "001.00")
  private Double cylinderCapacity;

  @NotNull
  @DecimalMax(value = "100000000.00")
  @DecimalMin(value = "000000001.00")
  private Double value;

  @ToString.Exclude
  private List<CreditRequestDto> creditRequests;
}
