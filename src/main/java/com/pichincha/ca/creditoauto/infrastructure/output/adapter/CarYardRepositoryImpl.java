package com.pichincha.ca.creditoauto.infrastructure.output.adapter;

import com.pichincha.ca.creditoauto.application.output.port.CarYardRepository;
import com.pichincha.ca.creditoauto.domain.CarYard;
import com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.CarYardJpaRepository;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper.CarYardRepositoryMapper;
import java.util.ResourceBundle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardRepositoryImpl.java
 * @class_description Implements the CarYardRepository interface to consume car yards from a
 * relational database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
@AllArgsConstructor
public class CarYardRepositoryImpl implements CarYardRepository {

  private static final String NOT_FOUND_KEY = "carYard.notFound";

  private ResourceBundle resourceBundle;
  private CarYardJpaRepository repository;

  @Override
  public CarYard findById(Long id) {
    return CarYardRepositoryMapper.toDomain(repository.findById(id).orElseThrow(() -> {
      throw new NotFoundException(resourceBundle.getString(NOT_FOUND_KEY));
    }));
  }

  @Override
  public CarYard findByIdWithClients(Long id) {
    return CarYardRepositoryMapper.toDomain(repository.findByIdWithClients(id).orElseThrow(() -> {
      throw new NotFoundException(resourceBundle.getString(NOT_FOUND_KEY));
    }));
  }

  @Override
  public CarYard findByIdWithSellers(Long id) {
    return CarYardRepositoryMapper.toDomain(repository.findByIdWithSellers(id).orElseThrow(() -> {
      throw new NotFoundException(resourceBundle.getString(NOT_FOUND_KEY));
    }));
  }

  @Override
  public boolean existsById(Long id) {
    return repository.existsById(id);
  }

  @Override
  public CarYard save(CarYard carYard) {
    return CarYardRepositoryMapper.toDomain(
        repository.save(CarYardRepositoryMapper.toEntity(carYard)));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
