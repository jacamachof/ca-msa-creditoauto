package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto;

import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name BrandDto.java
 * @class_description Contains brand information associated with cars
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {

  @Min(1)
  private Long id;
  private String name;
}
