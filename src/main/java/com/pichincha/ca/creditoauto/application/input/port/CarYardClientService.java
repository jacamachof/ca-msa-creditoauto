package com.pichincha.ca.creditoauto.application.input.port;

import com.pichincha.ca.creditoauto.domain.CarYardClient;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardClientService.java
 * @class_description Defines interface methods for the car yard client service of the application
 * layer to be consumed by the input adapters
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface CarYardClientService {

  /**
   * Retrieves a Car Yard Client by its id.
   *
   * @param id ID
   * @return Car Yard Client
   * @throws NotFoundException when the car yard client was not found
   * @see com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException
   */
  CarYardClient findById(Long id);

  /**
   * Returns whether a CarYardClient with the given id exists
   *
   * @param id ID
   * @return True when exists, otherwise false
   */
  boolean existsById(Long id);

  /**
   * Creates a given CarYardClient
   *
   * @param carYardClient CarYardClient
   * @return The saved entity
   * @throws BadRequestException when the combination car yard id and client id already exists, * or
   *                             ids do not exist in the application
   */
  CarYardClient create(CarYardClient carYardClient);

  /**
   * Updates a given CarYardClient
   *
   * @param carYardClient CarYardClient
   * @throws NotFoundException   when the car yard client was not found
   * @throws BadRequestException when a new combination of car yard id and client id already exists,
   *                             or ids do not exist in the application
   */
  void update(CarYardClient carYardClient);

  /**
   * Deletes an existing CarYardClient by its id
   *
   * @param id ID
   * @throws NotFoundException when the car yard client was not found
   */
  void deleteById(Long id);
}
