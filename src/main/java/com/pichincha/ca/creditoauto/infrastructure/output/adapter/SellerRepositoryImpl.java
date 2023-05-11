package com.pichincha.ca.creditoauto.infrastructure.output.adapter;

import com.pichincha.ca.creditoauto.application.output.port.SellerRepository;
import com.pichincha.ca.creditoauto.domain.Seller;
import com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.SellerJpaRepository;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper.SellerRepositoryMapper;
import java.util.List;
import java.util.ResourceBundle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name SellerRepositoryImpl.java
 * @class_description Implements the SellerRepository interface to consume sellers from a relational
 * database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
@AllArgsConstructor
public class SellerRepositoryImpl implements SellerRepository {

  private ResourceBundle resourceBundle;
  private SellerJpaRepository repository;

  @Override
  public Seller findById(Long id) {
    return SellerRepositoryMapper.toDomain(repository.findById(id).orElseThrow(() -> {
      throw new NotFoundException(resourceBundle.getString("seller.notFound"));
    }));
  }

  @Override
  public List<Seller> findByCarYardId(Long id) {
    var sellers = repository.findByCarYard_Id(id);

    if (sellers.isEmpty()) {
      throw new NotFoundException(resourceBundle.getString("sellers.notFoundByCarYard"));
    }

    return SellerRepositoryMapper.toDomainList(sellers);
  }

  @Override
  public boolean existsById(Long id) {
    return repository.existsById(id);
  }

  @Override
  public boolean existsByCarYardId(Long id) {
    return repository.existsByCarYard_Id(id);
  }

  @Override
  public Seller save(Seller sel) {
    return SellerRepositoryMapper.toDomain(
        repository.save(SellerRepositoryMapper.toEntity(sel)));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
