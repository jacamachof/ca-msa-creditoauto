package com.pichincha.ca.creditoauto.infrastructure.output.adapter;

import com.pichincha.ca.creditoauto.application.output.port.CarYardClientRepository;
import com.pichincha.ca.creditoauto.domain.CarYardClient;
import com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper.CarYardClientJpaRepository;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper.CarYardClientRepositoryMapper;
import java.util.ResourceBundle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardClientRepositoryImpl.java
 * @class_description Implements the CarYardClientRepository interface to consume car yard clients
 * from a relational database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
@AllArgsConstructor
public class CarYardClientRepositoryImpl implements CarYardClientRepository {

  private ResourceBundle resourceBundle;
  private CarYardClientJpaRepository repository;

  @Override
  public CarYardClient findById(Long id) {
    return CarYardClientRepositoryMapper.toDomain(repository.findById(id).orElseThrow(() -> {
      throw new NotFoundException(resourceBundle.getString("carYardClient.notFound"));
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
  public CarYardClient save(CarYardClient carYardClient) {
    return CarYardClientRepositoryMapper.toDomain(
        repository.save(CarYardClientRepositoryMapper.toEntity(carYardClient)));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
