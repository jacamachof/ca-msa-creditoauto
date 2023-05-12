package com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper;

import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.CarYardClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardClientJpaRepository.java
 * @class_description Contains CRUD queries to consume Car Yards Clients from the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
public interface CarYardClientJpaRepository extends CrudRepository<CarYardClientEntity, Long> {

  boolean existsByCarYardId(Long id);
}
