package com.pichincha.ca.creditoauto.application.output.port;

import com.pichincha.ca.creditoauto.domain.CarYard;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardRepository.java
 * @class_description Defines interface methods to consume car yards from external sources
 * regardless of their inner implementation
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface CarYardRepository {

  /**
   * Retrieves a CarYard by its id.
   *
   * @param id ID
   * @return CarYard
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
   * Saves a given CarYard
   *
   * @param carYard CarYard
   * @return The saved entity
   */
  CarYard save(CarYard carYard);

  /**
   * Deletes an existing CarYard by its id
   *
   * @param id ID
   */
  void deleteById(Long id);
}
