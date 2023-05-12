package com.pichincha.ca.creditoauto.application.input.port;

import com.pichincha.ca.creditoauto.domain.CreditRequest;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestService.java
 * @class_description Defines interface methods for the credit request service of the application
 * layer to be consumed by the input adapters
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface CreditRequestService {

  /**
   * Retrieves a credit request by its id.
   *
   * @param id ID
   * @return Credit request
   * @throws NotFoundException when the credit request id was not found
   * @see com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException
   */
  CreditRequest findById(Long id);

  /**
   * Creates a given create request
   *
   * @param creditRequest Credit request
   * @return The saved entity
   * @throws BadRequestException when the client already issued a credit request for the day, or
   *                             there are invalid ids in the request, or the vehicle has an active
   *                             credit request, or the seller does not belong to the car yards
   */
  CreditRequest create(CreditRequest creditRequest);

  /**
   * Updates crete request
   *
   * @param creditRequest Credit request
   * @throws NotFoundException when the credit request id was not found
   * @see com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException
   */
  void update(CreditRequest creditRequest);
}
