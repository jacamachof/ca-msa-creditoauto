package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper;

import com.pichincha.ca.creditoauto.domain.Client;
import com.pichincha.ca.creditoauto.domain.enums.GenderEnum;
import com.pichincha.ca.creditoauto.domain.enums.MaritalStatusEnum;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.ClientDto;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name BrandRestMapper.java
 * @class_description Class to convert Person DTO objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class ClientRestMapper {

  private ClientRestMapper() {
    throw new AssertionError();
  }

  public static ClientDto toDto(Client domain) {
    if (domain == null) {
      return null;
    }

    return ClientDto.builder()
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
        .creditApproval(domain.getCreditApproval())
        .creditRequests(CreditRequestRestMapper.toDtoList(domain.getCreditRequests()))
        .build();
  }

  public static Client toDomain(ClientDto dto) {
    if (dto == null) {
      return null;
    }

    var domain = new Client();
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
    domain.setCreditApproval(dto.getCreditApproval());
    return domain;
  }

  public static List<ClientDto> toDtoList(List<Client> domains) {
    if (domains == null) {
      return null;
    }

    var dtos = new ArrayList<ClientDto>();

    for (var domain : domains) {
      dtos.add(toDto(domain));
    }

    return dtos;
  }

  public static List<Client> toDomainList(List<ClientDto> dtos) {
    if (dtos == null) {
      return null;
    }

    var domains = new ArrayList<Client>();

    for (var dto : dtos) {
      domains.add(toDomain(dto));
    }

    return domains;
  }
}
