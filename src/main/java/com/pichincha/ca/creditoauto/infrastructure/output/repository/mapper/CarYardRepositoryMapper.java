package com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper;

import com.pichincha.ca.creditoauto.domain.CarYard;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.CarYardEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardRepositoryMapper.java
 * @class_description Class to convert CarYard entity objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class CarYardRepositoryMapper {

  private CarYardRepositoryMapper() {
    throw new AssertionError();
  }

  /**
   * The method converts the given CarYard entity object to CarYard domain object
   *
   * @param entity CarYardEntity object
   * @return CarYard Returns CarYard object
   */
  public static CarYard toDomain(CarYardEntity entity) {
    if (entity == null) {
      return null;
    }

    var domain = new CarYard();

    domain.setId(entity.getId());
    domain.setName(entity.getName());
    domain.setAddress(entity.getAddress());
    domain.setPhoneNumber(entity.getPhoneNumber());
    domain.setPointOfSale(entity.getPointOfSale());

    return domain;
  }

  /**
   * The method converts the given list of CarYard entity objects to a list of CarYard domain
   * objects
   *
   * @param entities List of CarYardEntity objects
   * @return List(CarYard) Returns a list of CarYard objects
   */
  public static List<CarYard> toDomainList(List<CarYardEntity> entities) {
    if (entities == null) {
      return null;
    }

    var domains = new ArrayList<CarYard>();

    for (var entity : entities) {
      domains.add(toDomain(entity));
    }

    return domains;
  }

  /**
   * The method converts the given CarYard domain object to CarYard entity object
   *
   * @param domain CarYard object
   * @return CarYardEntity Returns CarYardEntity object
   */
  public static CarYardEntity toEntity(CarYard domain) {
    if (domain == null) {
      return null;
    }

    var entity = new CarYardEntity();

    entity.setId(domain.getId());
    entity.setName(domain.getName());
    entity.setAddress(domain.getAddress());
    entity.setPhoneNumber(domain.getPhoneNumber());
    entity.setPointOfSale(domain.getPointOfSale());

    return entity;
  }

  /**
   * The method converts the given list of CarYard domain objects to a list of CarYard entity
   * objects
   *
   * @param domains List of CarYards objects
   * @return List(CarYardEntity) Returns a list of CarYardEntity objects
   */
  public static List<CarYardEntity> toEntityList(List<CarYard> domains) {
    if (domains == null) {
      return null;
    }

    var entities = new ArrayList<CarYardEntity>();

    for (var domain : domains) {
      entities.add(toEntity(domain));
    }

    return entities;
  }
}
