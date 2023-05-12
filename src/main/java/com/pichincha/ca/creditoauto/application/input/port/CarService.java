package com.pichincha.ca.creditoauto.application.input.port;

import com.pichincha.ca.creditoauto.domain.Car;

/**
 * @author jocamach@pichincha.com
 * @class_name CarService.java
 * @class_description Defines interface methods for the car service of the application layer to be
 * consumed by the input adapters
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface CarService {

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
   * Returns whether a Car with the given id exists
   *
   * @param id ID
   * @return True when exists, otherwise false
   */
  boolean existsById(Long id);

  /**
   * Creates a given Car
   *
   * @param car Car
   * @return The saved entity
   * @throws BadRequestException when the car plate already exists or the brand id is not valid
   */
  Car create(Car car);

  /**
   * Updates a given Car
   *
   * @param car Car
   * @throws NotFoundException   when the car id was not found
   * @throws BadRequestException when the brand id is not valid, or the new plate already exists, or
   *                             the car has ongoing or dispatched credit requests
   */
  void update(Car car);

  /**
   * Deletes an existing car by its ID
   *
   * @param id ID
   * @throws NotFoundException   when the car id was not found
   * @throws BadRequestException when the car has ongoing or dispatched credit requests
   */
  void deleteById(Long id);

  /**
   * Validates whether a car has active credit requests
   *
   * @param car Car
   * @throws BadRequestException when the car has ctive credit requests
   */
  void validateCreditRequests(Car car);
}
