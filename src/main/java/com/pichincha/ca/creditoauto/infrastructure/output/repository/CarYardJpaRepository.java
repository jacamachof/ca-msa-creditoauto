package com.pichincha.ca.creditoauto.infrastructure.output.repository;

import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.CarYardEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardJpaRepository.java
 * @class_description Contains CRUD queries to consume Car Yards from the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
public interface CarYardJpaRepository extends CrudRepository<CarYardEntity, Long> {

  @Query("SELECT c FROM CarYardEntity c LEFT JOIN FETCH c.clients cl WHERE c.id = :id")
  Optional<CarYardEntity> findByIdWithClients(Long id);

  @Query("SELECT c FROM CarYardEntity c LEFT JOIN FETCH c.sellers s WHERE c.id = :id")
  Optional<CarYardEntity> findByIdWithSellers(Long id);
}
