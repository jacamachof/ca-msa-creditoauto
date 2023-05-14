package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardDto.java
 * @class_description Contains car yard information
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarYardDto {

  @Min(1)
  private Long id;

  @NotBlank
  @Size(min = 1, max = 255)
  private String name;

  @NotBlank
  @Size(min = 1, max = 512)
  private String address;

  @NotBlank
  @Size(min = 1, max = 20)
  @Pattern(regexp = "^[0-9]*$")
  private String phoneNumber;

  @NotBlank
  @Size(min = 1, max = 255)
  private String pointOfSale;
}
