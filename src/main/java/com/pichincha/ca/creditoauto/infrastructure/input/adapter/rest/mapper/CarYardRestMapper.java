package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper;

import com.pichincha.ca.creditoauto.domain.CarYard;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.CarYardDto;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardRestMapper.java
 * @class_description Class to convert CarYard DTO objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class CarYardRestMapper {

  private CarYardRestMapper() {
    throw new AssertionError();
  }

  public static CarYardDto toDto(CarYard domain) {
    if (domain == null) {
      return null;
    }

    return CarYardDto.builder()
        .id(domain.getId())
        .name(domain.getName())
        .address(domain.getAddress())
        .phoneNumber(domain.getPhoneNumber())
        .pointOfSale(domain.getPointOfSale())
        .build();
  }

  public static CarYard toDomain(CarYardDto dto) {
    if (dto == null) {
      return null;
    }

    var domain = new CarYard();
    domain.setId(dto.getId());
    domain.setName(dto.getName());
    domain.setAddress(dto.getAddress());
    domain.setPhoneNumber(dto.getPhoneNumber());
    domain.setPointOfSale(dto.getPointOfSale());
    return domain;
  }

  public static List<CarYardDto> toDtoList(List<CarYard> domains) {
    if (domains == null) {
      return null;
    }

    var dtos = new ArrayList<CarYardDto>();

    for (var domain : domains) {
      dtos.add(toDto(domain));
    }

    return dtos;
  }

  public static List<CarYard> toDomainList(List<CarYardDto> dtos) {
    if (dtos == null) {
      return null;
    }

    var domains = new ArrayList<CarYard>();

    for (var dto : dtos) {
      domains.add(toDomain(dto));
    }

    return domains;
  }
}
