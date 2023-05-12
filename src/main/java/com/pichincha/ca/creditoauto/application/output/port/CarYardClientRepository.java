package com.pichincha.ca.creditoauto.application.output.port;

import com.pichincha.ca.creditoauto.domain.CarYardClient;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardClientRepository.java
 * @class_description Defines interface methods to consume car yard clients from external sources
 * regardless of their inner implementation
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface CarYardClientRepository {

  /**
   * Retrieves a CarYardClient by its id.
   *
   * @param id ID
   * @return CarYardClient
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
   * Returns whether a CarYardClient associated with the given car yard id exists
   *
   * @param id Car yard ID
   * @return True when exists, otherwise false
   */
  boolean existsByCarYardId(Long id);

  /**
   * Saves a given CarYardClient
   *
   * @param carYardClient CarYardClient
   * @return The saved entity
   */
  CarYardClient save(CarYardClient carYardClient);

  /**
   * Deletes an existing CarYardClient by its id
   *
   * @param id ID
   */
  void deleteById(Long id);
}
