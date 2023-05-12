package com.pichincha.ca.creditoauto.application.input.port;

import com.pichincha.ca.creditoauto.domain.Seller;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name SellerService.java
 * @class_description Defines interface methods for the seller service of the application layer to
 * be consumed by the input adapters
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface SellerService {

  /**
   * Retrieves a Seller by its id.
   *
   * @param id ID
   * @return Seller
   * @throws NotFoundException when the seller was not found
   * @see com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException
   */
  Seller findById(Long id);

  /**
   * Retrieves all sellers
   *
   * @return Returns a list of sellers
   */
  List<Seller> findAll();

  /**
   * Returns whether a Seller with the given id exists
   *
   * @param id ID
   * @return True when exists, otherwise false
   */
  boolean existsById(Long id);

  /**
   * Creates a given Seller
   *
   * @param seller Seller
   * @return The saved entity
   * @throws BadRequestException when the seller identification already exists
   */
  Seller create(Seller seller);

  /**
   * Create sellers
   *
   * @param sellers List of sellers
   */
  void saveAll(List<Seller> sellers);
}
