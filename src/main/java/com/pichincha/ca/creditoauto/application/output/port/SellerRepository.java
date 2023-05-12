package com.pichincha.ca.creditoauto.application.output.port;

import com.pichincha.ca.creditoauto.domain.Seller;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name SellerRepository.java
 * @class_description Defines interface methods to consume sellers from external sources regardless
 * of their inner implementation
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface SellerRepository {

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
   * Returns whether a Seller with the given name exists
   *
   * @param identification Name
   * @return True when exists, otherwise false
   */
  boolean existsByIdentification(String identification);

  /**
   * Returns whether a Seller associated with the given car yard id exists
   *
   * @param id Car yard ID
   * @return True when exists, otherwise false
   */
  boolean existsByCarYardId(Long id);

  /**
   * Saves a given Seller
   *
   * @param seller Seller
   * @return The saved entity
   */
  Seller save(Seller seller);

  /**
   * Deletes an existing Seller by its id
   *
   * @param id ID
   */
  void deleteById(Long id);
}
