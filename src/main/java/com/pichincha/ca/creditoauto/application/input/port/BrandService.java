package com.pichincha.ca.creditoauto.application.input.port;

import com.pichincha.ca.creditoauto.domain.Brand;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name BrandService.java
 * @class_description Defines interface methods for the brand service of the application layer to be
 * consumed by the input adapters
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface BrandService {

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
   * Retrieves all brands
   *
   * @return Returns a list of brands
   */
  List<Brand> findAll();

  /**
   * Returns whether a Brand with the given id exists
   *
   * @param id ID
   * @return True when exists, otherwise false
   */
  boolean existsById(Long id);

  /**
   * Creates a given Brand
   *
   * @param brand Brand
   * @return The saved entity
   * @throws BadRequestException when the brand name is missing or already exists
   */
  Brand create(Brand brand);
}
