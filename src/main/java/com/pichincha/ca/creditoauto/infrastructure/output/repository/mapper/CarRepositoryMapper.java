package com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper;

import com.pichincha.ca.creditoauto.domain.Car;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.CarEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Persistence;

/**
 * @author jocamach@pichincha.com
 * @class_name CarRepositoryMapper.java
 * @class_description Class to convert Car entity objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class CarRepositoryMapper {

  private CarRepositoryMapper() {
    throw new AssertionError();
  }

  /**
   * The method converts the given Car entity object to Car domain object
   *
   * @param entity CarEntity object
   * @return Car Returns Car object
   */
  public static Car toDomain(CarEntity entity) {
    if (entity == null) {
      return null;
    }

    var domain = new Car();

    domain.setId(entity.getId());
    domain.setPlate(entity.getPlate());
    domain.setModel(entity.getModel());
    domain.setVin(entity.getVin());
    domain.setType(entity.getType());
    domain.setValue(entity.getValue());
    domain.setCylinderCapacity(entity.getCylinderCapacity());
    domain.setBrand(BrandRepositoryMapper.toDomain(entity.getBrand()));

    if (Objects.nonNull(entity.getCreditRequests()) &&
        Persistence.getPersistenceUtil().isLoaded(entity, "creditRequests")) {
      domain.setCreditRequests(
          CreditRequestRepositoryMapper.toDomainList(entity.getCreditRequests()));
    }

    return domain;
  }

  /**
   * The method converts the given list of Car entity objects to a list of Car domain objects
   *
   * @param entities List of CarEntity objects
   * @return List(Car) Returns a list of Car objects
   */
  public static List<Car> toDomainList(List<CarEntity> entities) {
    if (entities == null) {
      return null;
    }

    var domains = new ArrayList<Car>();

    for (var entity : entities) {
      domains.add(toDomain(entity));
    }

    return domains;
  }

  /**
   * The method converts the given Car domain object to Car entity object
   *
   * @param domain Car object
   * @return CarEntity Returns CarEntity object
   */
  public static CarEntity toEntity(Car domain) {
    if (domain == null) {
      return null;
    }

    var entity = new CarEntity();

    entity.setId(domain.getId());
    entity.setPlate(domain.getPlate());
    entity.setModel(domain.getModel());
    entity.setVin(domain.getModel());
    entity.setType(domain.getType());
    entity.setValue(domain.getValue());
    entity.setCylinderCapacity(domain.getCylinderCapacity());
    entity.setBrand(BrandRepositoryMapper.toEntity(domain.getBrand()));

    return entity;
  }

  /**
   * The method converts the given list of Car domain objects to a list of Car entity objects
   *
   * @param domains List of Car objects
   * @return List(CarEntity) Returns a list of CarEntity objects
   */
  public static List<CarEntity> toEntityList(List<Car> domains) {
    if (domains == null) {
      return null;
    }

    var entities = new ArrayList<CarEntity>();

    for (var domain : domains) {
      entities.add(toEntity(domain));
    }

    return entities;
  }
}
