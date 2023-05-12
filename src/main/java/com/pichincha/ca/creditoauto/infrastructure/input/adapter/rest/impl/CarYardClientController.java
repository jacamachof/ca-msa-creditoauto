package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.impl;

import com.pichincha.ca.creditoauto.application.input.port.CarYardClientService;
import com.pichincha.ca.creditoauto.infrastructure.exception.BusinessException;
import com.pichincha.ca.creditoauto.infrastructure.exception.UnexpectedException;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.CarYardClientDto;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper.CarYardClientRestMapper;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardClientController.java
 * @class_description Class that implements a REST controller for clients associated with car yards
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/carYardClients")
@AllArgsConstructor
public class CarYardClientController {

  private CarYardClientService carYardClientService;

  /**
   * Fetches a car yard client by its id
   *
   * @param id ID
   * @return CardYardClientDto
   * @throws BusinessException   when the car yard client was not found
   * @throws UnexpectedException when an internal server error was thrown
   */
  @GetMapping("/get")
  public CarYardClientDto getCarYardClientClient(@RequestParam @Min(1) Long id) {
    CarYardClientDto response;

    log.info("Invoking CarYardClientController.getCarYardClient: {}", id);

    try {
      response = CarYardClientRestMapper.toDto(carYardClientService.findById(id));
    } catch (BusinessException e) {
      log.warn("Business exception for CarYardClientController.getCarYardClient: {}. {}",
          id, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarYardClientController.getCarYardClient: {}. {}",
          e.getClass().getSimpleName(), id, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarYardClientController.getCarYardClient: {} response: {}", id, response);

    return response;
  }

  /**
   * Creates a car yard client
   *
   * @param carYardClient CarYardClientDto
   * @return CardYardClientDto with id
   * @throws BusinessException   when the combination car yard id and client id already exists, * or
   *                             ids do not exist in the application
   * @throws UnexpectedException when an internal server error was thrown
   */
  @PostMapping("/post")
  public CarYardClientDto postCarYardClientClient(
      @Valid @RequestBody CarYardClientDto carYardClient) {
    CarYardClientDto response;

    log.info("Invoking CarYardClientController.postCarYardClient: {}", carYardClient);

    try {
      response = CarYardClientRestMapper.toDto(
          carYardClientService.create(CarYardClientRestMapper.toDomain(carYardClient)));
    } catch (BusinessException e) {
      log.warn("Business exception for CarYardClientController.postCarYardClient: {}. {}",
          carYardClient, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarYardClientController.postCarYardClient: {}. {}",
          e.getClass().getSimpleName(), carYardClient, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarYardClientController.postCarYardClient: {} response: {}", carYardClient, response);

    return response;
  }

  /**
   * Updates a car yard client
   *
   * @param carYardClient CarYardClientDto
   * @throws BusinessException   when a new combination of car yard id and client id already exists,
   *                             or ids do not exist in the application
   * @throws UnexpectedException when an internal server error was thrown
   */
  @PatchMapping("/patch")
  public void patchCarYardClientClient(@Valid @RequestBody CarYardClientDto carYardClient) {
    log.info("Invoking CarYardClientController.patchCarYardClient: {}", carYardClient);

    try {
      carYardClientService.update(CarYardClientRestMapper.toDomain(carYardClient));
    } catch (BusinessException e) {
      log.warn("Business exception for CarYardClientController.patchCarYardClient: {}. {}",
          carYardClient, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarYardClientController.patchCarYardClient: {}. {}",
          e.getClass().getSimpleName(), carYardClient, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarYardClientController.patchCarYardClient: {} updated successfully", carYardClient);
  }

  /**
   * Deletes a car yard client
   *
   * @param id ID
   * @throws BusinessException   when the car yard client was not found
   * @throws UnexpectedException when an internal server error was thrown
   */
  @DeleteMapping("/delete")
  public void deleteCarYardClient(@RequestParam @Min(1) Long id) {
    log.info("Invoking CarYardClientController.deleteCarYardClient: {}", id);

    try {
      carYardClientService.deleteById(id);
    } catch (BusinessException e) {
      log.warn("Business exception for CarYardClientController.deleteCarYardClient: {}. {}",
          id, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarYardClientController.deleteCarYardClient: {}. {}",
          e.getClass().getSimpleName(), id, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarYardClientController.deleteCarYardClient: {} deleted successfully", id);
  }

}
