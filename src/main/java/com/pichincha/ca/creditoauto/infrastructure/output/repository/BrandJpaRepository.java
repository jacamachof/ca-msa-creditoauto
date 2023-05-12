package com.pichincha.ca.creditoauto.infrastructure.output.repository;

import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.BrandEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name BrandJpaRepository.java
 * @class_description Contains CRUD queries to consume Brands from the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
public interface BrandJpaRepository extends CrudRepository<BrandEntity, Long> {

  boolean existsByName(String name);

  @Query("SELECT b FROM BrandEntity b WHERE b.name IN :names")
  List<BrandEntity> findByNames(List<String> names);
}
