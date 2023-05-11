package com.pichincha.ca.creditoauto.application.output.port;

import com.pichincha.ca.creditoauto.domain.Brand;

/**
 * @author jocamach@pichincha.com
 * @class_name BrandRepository.java
 * @class_description Defines interface methods to consume brands from external sources regardless
 * of their inner implementation
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface BrandRepository {

  /**
   * Retrieves a Brand by its id.
   *
   * @param id ID
   * @return Brand
   * @throws NotFoundException when the brand was not found
   * @see com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException
   */
  Brand findById(Long id);

  /**
   * Returns whether a Brand with the given id exists
   *
   * @param id ID
   * @return True when exists, otherwise false
   */
  boolean existsById(Long id);

  /**
   * Saves a given Brand
   *
   * @param brand Brand
   * @return The saved entity
   */
  Brand save(Brand brand);

  /**
   * Deletes an existing Brand by its id
   *
   * @param id ID
   */
  void deleteById(Long id);
}