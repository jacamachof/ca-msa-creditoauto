package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper;

import com.pichincha.ca.creditoauto.domain.Car;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.CarDto;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name CarRestMapper.java
 * @class_description Class to convert Car DTO objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class CarRestMapper {

  private CarRestMapper() {
    throw new AssertionError();
  }

  public static CarDto toDto(Car domain) {
    if (domain == null) {
      return null;
    }

    return CarDto.builder()
        .id(domain.getId())
        .plate(domain.getPlate())
        .model(domain.getModel())
        .vin(domain.getVin())
        .brand(BrandRestMapper.toDto(domain.getBrand()))
        .type(domain.getType())
        .cylinderCapacity(domain.getCylinderCapacity())
        .value(domain.getValue())
        .creditRequests(CreditRequestRestMapper.toDtoList(domain.getCreditRequests()))
        .build();
  }

  public static Car toDomain(CarDto dto) {
    if (dto == null) {
      return null;
    }

    var domain = new Car();
    domain.setId(dto.getId());
    domain.setPlate(dto.getPlate());
    domain.setModel(dto.getModel());
    domain.setVin(dto.getVin());
    domain.setBrand(BrandRestMapper.toDomain(dto.getBrand()));
    domain.setType(dto.getType());
    domain.setCylinderCapacity(dto.getCylinderCapacity());
    domain.setValue(dto.getValue());
    return domain;
  }

  public static List<CarDto> toDtoList(List<Car> domains) {
    if (domains == null) {
      return null;
    }

    var dtos = new ArrayList<CarDto>();

    for (var domain : domains) {
      dtos.add(toDto(domain));
    }

    return dtos;
  }

  public static List<Car> toDomainList(List<CarDto> dtos) {
    if (dtos == null) {
      return null;
    }

    var domains = new ArrayList<Car>();

    for (var dto : dtos) {
      domains.add(toDomain(dto));
    }

    return domains;
  }
}
