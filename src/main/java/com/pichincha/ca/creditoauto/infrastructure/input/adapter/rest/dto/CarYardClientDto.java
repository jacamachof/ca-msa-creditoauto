package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;
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
 * @class_name CarYardDto.java
 * @class_description Contains information between car yards and clients
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarYardClientDto {

  @Min(1)
  private Long id;

  @NotNull
  private CarYardDto carYard;

  @NotNull
  private ClientDto client;

  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
  private ZonedDateTime assignedDate;
}
