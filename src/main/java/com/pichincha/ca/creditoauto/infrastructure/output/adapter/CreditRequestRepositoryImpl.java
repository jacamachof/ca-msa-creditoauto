package com.pichincha.ca.creditoauto.infrastructure.output.adapter;

import com.pichincha.ca.creditoauto.application.output.port.CreditRequestRepository;
import com.pichincha.ca.creditoauto.domain.CreditRequest;
import com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.CreditRequestJpaRepository;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper.CreditRequestRepositoryMapper;
import java.util.ResourceBundle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestRepositoryImpl.java
 * @class_description Implements the CreditRequestRepository interface to consume credit requests
 * from a relational database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
@AllArgsConstructor
public class CreditRequestRepositoryImpl implements CreditRequestRepository {

  private ResourceBundle resourceBundle;
  private CreditRequestJpaRepository repository;

  @Override
  public CreditRequest findById(Long id) {
    return CreditRequestRepositoryMapper.toDomain(
        repository.findById(id).orElseThrow(() -> {
          throw new NotFoundException(resourceBundle.getString("creditRequest.notFound"));
        }));
  }

  @Override
  public boolean existsById(Long id) {
    return repository.existsById(id);
  }

  @Override
  public boolean existsByCarYardId(Long id) {
    return repository.existsByCarYardId(id);
  }

  @Override
  public CreditRequest save(CreditRequest creditRequest) {
    return CreditRequestRepositoryMapper.toDomain(
        repository.save(CreditRequestRepositoryMapper.toEntity(creditRequest)));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
