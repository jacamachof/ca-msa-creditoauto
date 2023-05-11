package com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper;

import com.pichincha.ca.creditoauto.domain.Seller;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.SellerEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name SellerRepositoryMapper.java
 * @class_description Class to convert Seller entity objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class SellerRepositoryMapper {

  private SellerRepositoryMapper() {
    throw new AssertionError();
  }

  /**
   * The method converts the given Seller entity object to Seller domain object
   *
   * @param entity SellerEntity object
   * @return Seller Returns Seller object
   */
  public static Seller toDomain(SellerEntity entity) {
    if (entity == null) {
      return null;
    }

    var domain = new Seller();

    PersonRepositoryMapper.toDomain(domain, entity);
    domain.setCarYard(CarYardRepositoryMapper.toDomain(entity.getCarYard()));

    return domain;
  }

  /**
   * The method converts the given list of Seller entity objects to a list of Seller domain objects
   *
   * @param entities List of SellerEntity objects
   * @return List(Seller) Returns a list of Seller objects
   */
  public static List<Seller> toDomainList(List<SellerEntity> entities) {
    if (entities == null) {
      return null;
    }

    var domains = new ArrayList<Seller>();

    for (var entity : entities) {
      domains.add(toDomain(entity));
    }

    return domains;
  }

  /**
   * The method converts the given Seller domain object to a Seller entity object
   *
   * @param domain Seller object
   * @return SellerEntity Returns SellerEntity object
   */
  public static SellerEntity toEntity(Seller domain) {
    if (domain == null) {
      return null;
    }

    var entity = new SellerEntity();

    PersonRepositoryMapper.toEntity(entity, domain);
    entity.setCarYard(CarYardRepositoryMapper.toEntity(domain.getCarYard()));

    return entity;
  }

  /**
   * The method converts the given list of Seller domain objects to a list of Seller entity objects
   *
   * @param domains List of Seller objects
   * @return List(SellerEntity) Returns a list of SellerEntity objects
   */
  public static List<SellerEntity> toEntityList(List<Seller> domains) {
    if (domains == null) {
      return null;
    }

    var entities = new ArrayList<SellerEntity>();

    for (var domain : domains) {
      entities.add(toEntity(domain));
    }

    return entities;
  }
}
