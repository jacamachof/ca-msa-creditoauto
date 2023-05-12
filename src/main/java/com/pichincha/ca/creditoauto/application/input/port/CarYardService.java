package com.pichincha.ca.creditoauto.application.input.port;

import com.pichincha.ca.creditoauto.domain.CarYard;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardService.java
 * @class_description Defines interface methods for the car yard service of the application layer to
 * be consumed by the input adapters
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface CarYardService {

  /**
   * Retrieves a Car Yard by its id.
   *
   * @param id ID
   * @return Car Yard
   * @throws NotFoundException when the car yard was not found
   * @see com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException
   */
  CarYard findById(Long id);

  /**
   * Returns whether a CarYard with the given id exists
   *
   * @param id ID
   * @return True when exists, otherwise false
   */
  boolean existsById(Long id);

  /**
   * Creates a given CarYard
   *
   * @param carYard CarYard
   * @return The saved entity
   * @throws BadRequestException when the car yard ID already exist
   */
  CarYard create(CarYard carYard);

  /**
   * Updates a given CarYard
   *
   * @param carYard CarYard
   * @throws NotFoundException   when the car yard was not found
   * @throws BadRequestException when the car yard ID is missing
   */
  void update(CarYard carYard);

  /**
   * Deletes an existing CarYard by its id
   *
   * @param id ID
   * @throws NotFoundException   when the car yard was not found
   * @throws BadRequestException when the car yard has clients or sellers assigned
   */
  void deleteById(Long id);
}
