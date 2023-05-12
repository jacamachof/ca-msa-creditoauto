package com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper;

import com.pichincha.ca.creditoauto.domain.CarYardClient;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.CarYardClientEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardClientRepositoryMapper.java
 * @class_description Class to convert CarYardClient entity objects to domain objects and
 * vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class CarYardClientRepositoryMapper {

  private CarYardClientRepositoryMapper() {
    throw new AssertionError();
  }

  /**
   * The method converts the given CarYardClient entity object to CarYardClient domain object
   *
   * @param entity CarYardClientEntity object
   * @return CarYardClient Returns CarYardClient object
   */
  public static CarYardClient toDomain(CarYardClientEntity entity) {
    if (entity == null) {
      return null;
    }

    var domain = new CarYardClient();

    domain.setId(entity.getId());
    domain.setCarYard(CarYardRepositoryMapper.toDomain(entity.getCarYard()));
    domain.setClient(ClientRepositoryMapper.toDomain(entity.getClient()));
    domain.setAssignedDate(entity.getAssignedDate());

    return domain;
  }

  /**
   * The method converts the given list of CarYardClient entity objects to a list of CarYardClient
   * domain objects
   *
   * @param entities List of CarYardClientEntity objects
   * @return List(CarYardClient) Returns a list of CarYardClient objects
   */
  public static List<CarYardClient> toDomainList(List<CarYardClientEntity> entities) {
    if (entities == null) {
      return null;
    }

    var domains = new ArrayList<CarYardClient>();

    for (var entity : entities) {
      domains.add(toDomain(entity));
    }

    return domains;
  }

  /**
   * The method converts the given CarYardClient domain object to CarYardClient entity object
   *
   * @param domain CarYardClient object
   * @return CarYardClientEntity Returns CarYardClientEntity object
   */
  public static CarYardClientEntity toEntity(CarYardClient domain) {
    var entity = new CarYardClientEntity();

    entity.setId(domain.getId());
    entity.setCarYard(CarYardRepositoryMapper.toEntity(domain.getCarYard()));
    entity.setClient(ClientRepositoryMapper.toEntity(domain.getClient()));
    entity.setAssignedDate(domain.getAssignedDate());

    return entity;
  }

  /**
   * The method converts the given list of CarYardClient domain objects to a list of CarYardClient
   * entity objects
   *
   * @param domains List of CarYardClient objects
   * @return List(CarYardClientEntity) Returns a list of CarYardClientEntity objects
   */
  public static List<CarYardClientEntity> toEntityList(List<CarYardClient> domains) {
    if (domains == null) {
      return null;
    }

    var entities = new ArrayList<CarYardClientEntity>();

    for (var domain : domains) {
      entities.add(toEntity(domain));
    }

    return entities;
  }
}
