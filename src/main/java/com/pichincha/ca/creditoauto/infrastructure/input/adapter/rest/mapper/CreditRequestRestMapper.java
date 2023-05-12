package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper;

import com.pichincha.ca.creditoauto.domain.CreditRequest;
import com.pichincha.ca.creditoauto.domain.enums.CreditRequestEnum;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.CreditRequestDto;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestRestMapper.java
 * @class_description Class to convert CreditRequest DTO objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class CreditRequestRestMapper {

  private CreditRequestRestMapper() {
    throw new AssertionError();
  }

  public static CreditRequestDto toDto(CreditRequest domain) {
    if (domain == null) {
      return null;
    }

    return CreditRequestDto.builder()
        .id(domain.getId())
        .car(CarRestMapper.toDto(domain.getCar()))
        .seller(SellerRestMapper.toDto(domain.getSeller()))
        .carYard(CarYardRestMapper.toDto(domain.getCarYard()))
        .installments(domain.getInstallments())
        .monthTerms(domain.getMonthTerms())
        .observation(domain.getObservation())
        .upfrontPayment(domain.getUpfrontPayment())
        .creationDate(domain.getCreationDate())
        .status(domain.getStatus() == null ? null : domain.getStatus().name())
        .build();
  }

  public static CreditRequest toDomain(CreditRequestDto dto) {
    if (dto == null) {
      return null;
    }

    var domain = new CreditRequest();
    domain.setId(dto.getId());
    domain.setCar(CarRestMapper.toDomain(dto.getCar()));
    domain.setClient(ClientRestMapper.toDomain(dto.getClient()));
    domain.setSeller(SellerRestMapper.toDomain(dto.getSeller()));
    domain.setCarYard(CarYardRestMapper.toDomain(dto.getCarYard()));
    domain.setInstallments(dto.getInstallments());
    domain.setMonthTerms(dto.getMonthTerms());
    domain.setObservation(dto.getObservation());
    domain.setUpfrontPayment(dto.getUpfrontPayment());
    domain.setCreationDate(dto.getCreationDate());
    domain.setStatus(CreditRequestEnum.of(dto.getStatus()));
    return domain;
  }

  public static List<CreditRequestDto> toDtoList(List<CreditRequest> domains) {
    if (domains == null) {
      return null;
    }

    var dtos = new ArrayList<CreditRequestDto>();

    for (var domain : domains) {
      dtos.add(toDto(domain));
    }

    return dtos;
  }

  public static List<CreditRequest> toDomainList(List<CreditRequestDto> dtos) {
    if (dtos == null) {
      return null;
    }

    var domains = new ArrayList<CreditRequest>();

    for (var dto : dtos) {
      domains.add(toDomain(dto));
    }

    return domains;
  }
}
