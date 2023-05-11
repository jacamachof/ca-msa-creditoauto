package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper;

import com.pichincha.ca.creditoauto.domain.Seller;
import com.pichincha.ca.creditoauto.domain.enums.GenderEnum;
import com.pichincha.ca.creditoauto.domain.enums.MaritalStatusEnum;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.SellerDto;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name SellerRestMapper.java
 * @class_description Class to convert Seller DTO objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class SellerRestMapper {

  private SellerRestMapper() {
    throw new AssertionError();
  }

  public static SellerDto toDto(Seller domain) {
    if (domain == null) {
      return null;
    }

    return SellerDto.builder()
        .id(domain.getId())
        .identification(domain.getIdentification())
        .firstName(domain.getFirstName())
        .lastName(domain.getLastName())
        .address(domain.getAddress())
        .localPhoneNumber(domain.getLocalPhoneNumber())
        .mobilePhoneNumber(domain.getMobilePhoneNumber())
        .birthdate(domain.getBirthdate())
        .gender(domain.getGender().name())
        .maritalStatus(domain.getMaritalStatus().name())
        .carYard(CarYardRestMapper.toDto(domain.getCarYard()))
        .build();
  }

  public static Seller toDomain(SellerDto dto) {
    if (dto == null) {
      return null;
    }

    var domain = new Seller();
    domain.setId(dto.getId());
    domain.setIdentification(dto.getIdentification());
    domain.setFirstName(dto.getFirstName());
    domain.setLastName(dto.getLastName());
    domain.setAddress(dto.getAddress());
    domain.setLocalPhoneNumber(dto.getLocalPhoneNumber());
    domain.setMobilePhoneNumber(dto.getMobilePhoneNumber());
    domain.setBirthdate(dto.getBirthdate());
    domain.setGender(GenderEnum.of(dto.getGender()));
    domain.setMaritalStatus(MaritalStatusEnum.of(dto.getMaritalStatus()));
    domain.setCarYard(CarYardRestMapper.toDomain(dto.getCarYard()));
    return domain;
  }

  public static List<SellerDto> toDtoList(List<Seller> domains) {
    if (domains == null) {
      return null;
    }

    var dtos = new ArrayList<SellerDto>();

    for (var domain : domains) {
      dtos.add(toDto(domain));
    }

    return dtos;
  }

  public static List<Seller> toDomainList(List<SellerDto> dtos) {
    if (dtos == null) {
      return null;
    }

    var domains = new ArrayList<Seller>();

    for (var dto : dtos) {
      domains.add(toDomain(dto));
    }

    return domains;
  }
}
