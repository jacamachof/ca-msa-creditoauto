package com.pichincha.ca.creditoauto.infrastructure.output.adapter;

import com.pichincha.ca.creditoauto.application.output.port.BrandRepository;
import com.pichincha.ca.creditoauto.domain.Brand;
import com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.BrandJpaRepository;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper.BrandRepositoryMapper;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name BrandRepositoryImpl.java
 * @class_description Implements the BrandRepository interface to consume brands from a relational
 * database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
@AllArgsConstructor
public class BrandRepositoryImpl implements BrandRepository {

  private ResourceBundle resourceBundle;
  private BrandJpaRepository repository;

  @Override
  public Brand findById(Long id) {
    return BrandRepositoryMapper.toDomain(repository.findById(id).orElseThrow(() -> {
      throw new NotFoundException(resourceBundle.getString("brand.notFound"));
    }));
  }

  @Override
  public List<Brand> findAll() {
    return BrandRepositoryMapper.toDomainList(
        StreamSupport.stream(repository.findAll().spliterator(), false)
            .collect(Collectors.toList()));
  }

  @Override
  public boolean existsById(Long id) {
    return repository.existsById(id);
  }

  @Override
  public boolean existsByName(String name) {
    return repository.existsByName(name);
  }

  @Override
  public Brand save(Brand brand) {
    return BrandRepositoryMapper.toDomain(
        repository.save(BrandRepositoryMapper.toEntity(brand)));
  }
}
