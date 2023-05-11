package com.pichincha.ca.creditoauto.infrastructure.output.adapter;

import com.pichincha.ca.creditoauto.application.output.port.CarRepository;
import com.pichincha.ca.creditoauto.application.output.port.ClientRepository;
import com.pichincha.ca.creditoauto.domain.Car;
import com.pichincha.ca.creditoauto.domain.Client;
import com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.CarJpaRepository;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.ClientJpaRepository;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper.CarRepositoryMapper;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper.ClientRepositoryMapper;
import java.util.List;
import java.util.ResourceBundle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name CarRepositoryImpl.java
 * @class_description Implements the CarRepository interface to consume cars from a relational
 * database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
@AllArgsConstructor
public class CarRepositoryImpl implements CarRepository {

  private ResourceBundle resourceBundle;
  private CarJpaRepository repository;

  @Override
  public Car findById(Long id) {
    return CarRepositoryMapper.toDomain(
        repository.findByIdWithCreditRequests(id).orElseThrow(() -> {
          throw new NotFoundException(resourceBundle.getString("car.notFound"));
        }));
  }

  @Override
  public boolean existsById(Long id) {
    return repository.existsById(id);
  }

  @Override
  public Car save(Car car) {
    return CarRepositoryMapper.toDomain(
        repository.save(CarRepositoryMapper.toEntity(car)));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}