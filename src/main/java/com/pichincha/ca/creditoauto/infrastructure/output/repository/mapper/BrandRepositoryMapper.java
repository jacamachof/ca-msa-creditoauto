package com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper;

import com.pichincha.ca.creditoauto.domain.Brand;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.BrandEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name BrandRepositoryMapper.java
 * @class_description Class to convert Brand entity objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class BrandRepositoryMapper {

  private BrandRepositoryMapper() {
    throw new AssertionError();
  }

  /**
   * The method converts the given Brand entity object to Brand domain object
   *
   * @param entity BrandEntity object
   * @return Brand Returns Brand object
   */
  public static Brand toDomain(BrandEntity entity) {
    if (entity == null) {
      return null;
    }

    var domain = new Brand();

    domain.setId(entity.getId());
    domain.setName(entity.getName());

    return domain;
  }

  /**
   * The method converts the given list of Brand entity objects to a list of Brand domain objects
   *
   * @param entities List of BrandEntity objects
   * @return List(Brand) Returns a list of Brand objects
   */
  public static List<Brand> toDomainList(List<BrandEntity> entities) {
    if (entities == null) {
      return null;
    }

    var domains = new ArrayList<Brand>();

    for (var entity : entities) {
      domains.add(toDomain(entity));
    }

    return domains;
  }

  /**
   * The method converts the given Brand domain to a Brand entity object
   *
   * @param domain Brand object
   * @return BrandEntity Returns Brand entity object
   */
  public static BrandEntity toEntity(Brand domain) {
    if (domain == null) {
      return null;
    }

    var entity = new BrandEntity();

    entity.setId(domain.getId());
    entity.setName(domain.getName());

    return entity;
  }

  /**
   * The method converts the given list of Brand domain objects to a list of Brand entity objects
   *
   * @param domains List of Brand objects
   * @return List(BrandEntity) Returns a list of BrandEntity objects
   */
  public static List<BrandEntity> toEntityList(List<Brand> domains) {
    if (domains == null) {
      return null;
    }

    var entities = new ArrayList<BrandEntity>();

    for (var domain : domains) {
      entities.add(toEntity(domain));
    }

    return entities;
  }
}
