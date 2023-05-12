package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.impl;

import com.pichincha.ca.creditoauto.application.input.port.CreditRequestService;
import com.pichincha.ca.creditoauto.infrastructure.exception.BusinessException;
import com.pichincha.ca.creditoauto.infrastructure.exception.UnexpectedException;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.CreditRequestDto;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper.CreditRequestRestMapper;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestController.java
 * @class_description Class that implements a REST controller for credit requests
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/creditRequests")
@AllArgsConstructor
public class CreditRequestController {

  private CreditRequestService creditRequestService;

  /**
   * Fetches a credit request by its ids
   *
   * @param id ID
   * @return CreditRequestDto
   * @throws BusinessException   when the credit request was not found
   * @throws UnexpectedException when an internal server error was thrown
   */
  @GetMapping("/get")
  public CreditRequestDto getCreditRequest(@RequestParam @Min(1) Long id) {
    CreditRequestDto response;

    log.info("Invoking CreditRequestController.getCreditRequest: {}", id);

    try {
      response = CreditRequestRestMapper.toDto(creditRequestService.findById(id));
    } catch (BusinessException e) {
      log.warn("Business exception for CreditRequestController.getCreditRequest: {}. {}",
          id, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CreditRequestController.getCreditRequest: {}. {}",
          e.getClass().getSimpleName(), id, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CreditRequestController.getCreditRequest: {} response: {}", id, response);

    return response;
  }

  /**
   * Creates a credit request
   *
   * @param creditRequest CreditRequestDto
   * @return CreditRequestDto with id
   * @throws BusinessException   when the client already issued a credit request for the day, or
   *                             there are invalid ids in the request, or the vehicle has an active
   *                             credit request, or the seller does not belong to the car yards
   * @throws UnexpectedException when an internal server error was thrown
   */
  @PostMapping("/post")
  public CreditRequestDto postCreditRequest(@Valid @RequestBody CreditRequestDto creditRequest) {
    CreditRequestDto response;

    log.info("Invoking CreditRequestController.postCreditRequest: {}", creditRequest);

    try {
      response = CreditRequestRestMapper.toDto(
          creditRequestService.create(CreditRequestRestMapper.toDomain(creditRequest)));
    } catch (BusinessException e) {
      log.warn("Business exception for CreditRequestController.postCreditRequest: {}. {}",
          creditRequest, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CreditRequestController.postCreditRequest: {}. {}",
          e.getClass().getSimpleName(), creditRequest, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CreditRequestController.postCreditRequest: {} response: {}", creditRequest, response);

    return response;
  }

  /**
   * Updates the status of a credit request and its basic fields
   *
   * @param creditRequest CreditRequestDto
   * @throws BusinessException   when the credit request was not found
   * @throws UnexpectedException when an internal server error was thrown
   */
  @PatchMapping("/patch")
  public void patchCreditRequest(@Valid @RequestBody CreditRequestDto creditRequest) {
    log.info("Invoking CreditRequestController.patchCreditRequest: {}", creditRequest);

    try {
      creditRequestService.update(CreditRequestRestMapper.toDomain(creditRequest));
    } catch (BusinessException e) {
      log.warn("Business exception for CreditRequestController.patchCreditRequest: {}. {}",
          creditRequest, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CreditRequestController.patchCreditRequest: {}. {}",
          e.getClass().getSimpleName(), creditRequest, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CreditRequestController.patchCreditRequest: {} updated successfully", creditRequest);
  }
}
