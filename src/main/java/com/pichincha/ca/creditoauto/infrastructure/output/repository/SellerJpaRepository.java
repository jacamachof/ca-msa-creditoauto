package com.pichincha.ca.creditoauto.infrastructure.output.repository;

import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.SellerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name SellerJpaRepository.java
 * @class_description Contains CRUD queries to consume Sellers from the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
public interface SellerJpaRepository extends CrudRepository<SellerEntity, Long> {

  boolean existsByCarYardId(Long id);

  boolean existsByIdentification(String identification);

  @Query("SELECT s FROM SellerEntity s WHERE s.identification IN :identifications")
  List<SellerEntity> findByIdentifications(List<String> identifications);
}
