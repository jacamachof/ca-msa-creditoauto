package com.pichincha.ca.creditoauto.infrastructure.output.repository;

import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.CarEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name CarJpaRepository.java
 * @class_description Contains CRUD queries to consume Cars from the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
public interface CarJpaRepository extends CrudRepository<CarEntity, Long> {

  @Query("SELECT c FROM CarEntity c JOIN FETCH c.creditRequests cr WHERE c.id = :id")
  Optional<CarEntity> findByIdWithCreditRequests(Long id);

  @Query("SELECT c FROM CarEntity c WHERE c.model = :model")
  List<CarEntity> findByModel(String model);

  @Query("SELECT c FROM CarEntity c WHERE c.brand.id = :id")
  List<CarEntity> findByBrandId(Long id);
}
