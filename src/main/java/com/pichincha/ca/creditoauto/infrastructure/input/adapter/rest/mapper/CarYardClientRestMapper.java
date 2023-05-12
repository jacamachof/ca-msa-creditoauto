package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper;

import com.pichincha.ca.creditoauto.domain.CarYardClient;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.CarYardClientDto;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardClientRestMapper.java
 * @class_description Class to convert CarYardClient DTO objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class CarYardClientRestMapper {

  private CarYardClientRestMapper() {
    throw new AssertionError();
  }

  public static CarYardClientDto toDto(CarYardClient domain) {
    if (domain == null) {
      return null;
    }

    return CarYardClientDto.builder()
        .id(domain.getId())
        .carYard(CarYardRestMapper.toDto(domain.getCarYard()))
        .client(ClientRestMapper.toDto(domain.getClient()))
        .assignedDate(domain.getAssignedDate())
        .build();
  }

  public static CarYardClient toDomain(CarYardClientDto dto) {
    if (dto == null) {
      return null;
    }

    var domain = new CarYardClient();
    domain.setId(dto.getId());
    domain.setCarYard(CarYardRestMapper.toDomain(dto.getCarYard()));
    domain.setClient(ClientRestMapper.toDomain(dto.getClient()));
    domain.setAssignedDate(dto.getAssignedDate());
    return domain;
  }

  public static List<CarYardClientDto> toDtoList(List<CarYardClient> domains) {
    if (domains == null) {
      return null;
    }

    var dtos = new ArrayList<CarYardClientDto>();

    for (var domain : domains) {
      dtos.add(toDto(domain));
    }

    return dtos;
  }

  public static List<CarYardClient> toDomainList(List<CarYardClientDto> dtos) {
    if (dtos == null) {
      return null;
    }

    var domains = new ArrayList<CarYardClient>();

    for (var dto : dtos) {
      domains.add(toDomain(dto));
    }

    return domains;
  }
}
