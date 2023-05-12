package com.pichincha.ca.creditoauto.infrastructure.output.repository;

import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.ClientEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name ClientJpaRepository.java
 * @class_description Contains CRUD queries to consume Clients from the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
public interface ClientJpaRepository extends CrudRepository<ClientEntity, Long> {

  @Query("SELECT c FROM ClientEntity c LEFT JOIN FETCH c.creditRequests cr WHERE c.id = :id")
  Optional<ClientEntity> findByIdWithCreditRequests(Long id);

  boolean existsByIdentification(String identification);
}
