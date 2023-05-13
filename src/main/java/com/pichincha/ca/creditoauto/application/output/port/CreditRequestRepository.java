package com.pichincha.ca.creditoauto.application.output.port;

import com.pichincha.ca.creditoauto.domain.CreditRequest;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestRepository.java
 * @class_description Defines interface methods to consume credit requests from external sources
 * regardless of their inner implementation
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface CreditRequestRepository {

  /**
   * Retrieves a Credit Request by its id.
   *
   * @param id ID
   * @return CreditRequest
   * @throws NotFoundException when the credit request was not found
   * @see com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException
   */
  CreditRequest findById(Long id);

  /**
   * Returns whether a Credit Request with the given id exists
   *
   * @param id ID
   * @return True when exists, otherwise false
   */
  boolean existsById(Long id);

  /**
   * Returns whether a Credit Request associated with the given car yard id exists
   *
   * @param id Car yard ID
   * @return True when exists, otherwise false
   */
  boolean existsByCarYardId(Long id);

  /**
   * Saves a given Credit Request
   *
   * @param creditRequest Credit Request
   * @return The saved entity
   */
  CreditRequest save(CreditRequest creditRequest);

  /**
   * Deletes an existing Credit Request by its id
   *
   * @param id ID
   */
  void deleteById(Long id);

  /**
   * Deletes credit requests by car id
   *
   * @param id Car id
   */
  void deleteByCarId(Long id);
}
