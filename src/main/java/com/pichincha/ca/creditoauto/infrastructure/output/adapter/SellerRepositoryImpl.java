package com.pichincha.ca.creditoauto.infrastructure.output.adapter;

import com.pichincha.ca.creditoauto.application.output.port.SellerRepository;
import com.pichincha.ca.creditoauto.domain.Seller;
import com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.SellerJpaRepository;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper.SellerRepositoryMapper;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
  public List<Seller> findAll() {
    return SellerRepositoryMapper.toDomainList(
        StreamSupport.stream(repository.findAll().spliterator(), false)
            .collect(Collectors.toList()));
  }

  @Override
  public boolean existsById(Long id) {
    return repository.existsById(id);
  }

  @Override
  public boolean existsByIdentification(String identification) {
    return repository.existsByIdentification(identification);
  }

  @Override
  public boolean existsByCarYardId(Long id) {
    return repository.existsByCarYardId(id);
  }

  @Override
  public Seller save(Seller seller) {
    return SellerRepositoryMapper.toDomain(
        repository.save(SellerRepositoryMapper.toEntity(seller)));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
