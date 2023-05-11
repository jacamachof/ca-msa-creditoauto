package com.pichincha.ca.creditoauto.application.output.port;

import com.pichincha.ca.creditoauto.domain.Car;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name CarRepository.java
 * @class_description Defines interface methods to consume cars from external sources regardless of
 * their inner implementation
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface CarRepository {

  /**
   * Retrieves a Car by its id.
   *
   * @param id ID
   * @return Car
   * @throws NotFoundException when the car was not found
   * @see com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException
   */
  Car findById(Long id);

  /**
   * Retrieves cars by model
   *
   * @param model Model
   * @return Returns cars with the given model
   */
  List<Car> findByModel(String model);

  /**
   * Retrieves cars by brand ID
   *
   * @param id Brand ID
   * @return Returns cars with the given brand ID
   */
  List<Car> findByBrandId(Long id);

  /**
   * Returns whether a Car with the given id exists
   *
   * @param id ID
   * @return True when exists, otherwise false
   */
  boolean existsById(Long id);

  /**
   * Saves a given Car
   *
   * @param car Car
   * @return The saved entity
   */
  Car save(Car car);

  /**
   * Deletes an existing Car by its id
   *
   * @param id ID
   */
  void deleteById(Long id);
}
