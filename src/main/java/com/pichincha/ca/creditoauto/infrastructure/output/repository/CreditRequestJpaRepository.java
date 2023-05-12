package com.pichincha.ca.creditoauto.infrastructure.output.repository;

import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.CreditRequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestJpaRepository.java
 * @class_description Contains CRUD queries to consume Credit Requests from the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
public interface CreditRequestJpaRepository extends CrudRepository<CreditRequestEntity, Long> {

  boolean existsByCarYardId(Long id);
}
