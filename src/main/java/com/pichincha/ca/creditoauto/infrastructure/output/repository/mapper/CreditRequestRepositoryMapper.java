package com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper;

import com.pichincha.ca.creditoauto.domain.CreditRequest;
import com.pichincha.ca.creditoauto.domain.enums.CreditRequestEnum;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.CreditRequestEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestRepositoryMapper.java
 * @class_description Class to convert CreditRequest entity objects to domain objects and
 * vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class CreditRequestRepositoryMapper {

  private CreditRequestRepositoryMapper() {
    throw new AssertionError();
  }

  /**
   * The method converts the given CreditRequest entity object to CreditRequest domain object
   *
   * @param entity CreditRequestEntity object
   * @return CreditRequest Returns CreditRequest object
   */
  public static CreditRequest toDomain(CreditRequestEntity entity) {
    if (entity == null) {
      return null;
    }

    var domain = new CreditRequest();

    domain.setId(entity.getId());
    domain.setCar(CarRepositoryMapper.toDomain(entity.getCar(), false));
    domain.setClient(ClientRepositoryMapper.toDomain(entity.getClient(), false));
    domain.setSeller(SellerRepositoryMapper.toDomain(entity.getSeller()));
    domain.setCarYard(CarYardRepositoryMapper.toDomain(entity.getCarYard()));
    domain.setInstallments(entity.getInstallments());
    domain.setMonthTerms(entity.getMonthTerms());
    domain.setObservation(entity.getObservation());
    domain.setUpfrontPayment(entity.getUpfrontPayment());
    domain.setCreationDate(entity.getCreationDate());

    if (Objects.nonNull(entity.getStatus())) {
      domain.setStatus(CreditRequestEnum.of(entity.getStatus()));
    }

    return domain;
  }

  /**
   * The method converts the given list of CreditRequest entity objects to a list of CreditRequest
   * domain objects
   *
   * @param entities List of CreditRequestEntity objects
   * @return List(CreditRequest) Returns a list of CreditRequest objects
   */
  public static List<CreditRequest> toDomainList(List<CreditRequestEntity> entities) {
    if (entities == null) {
      return null;
    }

    var domains = new ArrayList<CreditRequest>();

    for (var entity : entities) {
      domains.add(toDomain(entity));
    }

    return domains;
  }

  /**
   * The method converts the given CreditRequest domain object to CreditRequest entity object
   *
   * @param domain CreditRequest object
   * @return CreditRequestEntity Returns CreditRequestEntity object
   */
  public static CreditRequestEntity toEntity(CreditRequest domain) {
    if (domain == null) {
      return null;
    }

    var entity = new CreditRequestEntity();

    entity.setId(domain.getId());
    entity.setCar(CarRepositoryMapper.toEntity(domain.getCar()));
    entity.setClient(ClientRepositoryMapper.toEntity(domain.getClient()));
    entity.setSeller(SellerRepositoryMapper.toEntity(domain.getSeller()));
    entity.setCarYard(CarYardRepositoryMapper.toEntity(domain.getCarYard()));
    entity.setInstallments(domain.getInstallments());
    entity.setMonthTerms(domain.getMonthTerms());
    entity.setObservation(domain.getObservation());
    entity.setUpfrontPayment(domain.getUpfrontPayment());
    entity.setCreationDate(domain.getCreationDate());

    if (Objects.nonNull(domain.getStatus())) {
      entity.setStatus(domain.getStatus().name());
    }

    return entity;
  }

  /**
   * The method converts the given list of CreditRequest domain objects to a list of CreditRequest
   * entity objects
   *
   * @param domains List of CreditRequest objects
   * @return List(CreditRequestEntity) Returns a list of CreditRequestEntity objects
   */
  public static List<CreditRequestEntity> ToEntityList(List<CreditRequest> domains) {
    if (domains == null) {
      return null;
    }

    var entities = new ArrayList<CreditRequestEntity>();

    for (var domain : domains) {
      entities.add(toEntity(domain));
    }

    return entities;
  }
}
