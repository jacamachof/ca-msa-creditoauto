package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.impl;

import com.pichincha.ca.creditoauto.application.input.port.CarYardService;
import com.pichincha.ca.creditoauto.infrastructure.exception.BusinessException;
import com.pichincha.ca.creditoauto.infrastructure.exception.UnexpectedException;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.CarYardDto;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper.CarYardRestMapper;
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
 * @class_name CarYardController.java
 * @class_description Class that implements a REST controller for car yards
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/carYards")
@AllArgsConstructor
public class CarYardController {

  private CarYardService carYardService;

  /**
   * Fetches a car yard by its id
   *
   * @param id ID
   * @return CardYardDto
   * @throws BusinessException   when the car yard was not found
   * @throws UnexpectedException when an internal server error was thrown
   */
  @GetMapping("/get")
  public CarYardDto getCarYard(@RequestParam @Min(1) Long id) {
    CarYardDto response;

    log.info("Invoking CarYardController.getCarYard: {}", id);

    try {
      response = CarYardRestMapper.toDto(carYardService.findById(id));
    } catch (BusinessException e) {
      log.warn("Business exception for CarYardController.getCarYard: {}. {}",
          id, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarYardController.getCarYard: {}. {}",
          e.getClass().getSimpleName(), id, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarYardController.getCarYard: {} response: {}", id, response);

    return response;
  }

  /**
   * Creates a car yard
   *
   * @param carYard CarYardDto
   * @return CardYardDto with id
   * @throws BusinessException   when the car yard id already exist
   * @throws UnexpectedException when an internal server error was thrown
   */
  @PostMapping("/post")
  public CarYardDto postCarYard(@Valid @RequestBody CarYardDto carYard) {
    CarYardDto response;

    log.info("Invoking CarYardController.postCarYard: {}", carYard);

    try {
      response = CarYardRestMapper.toDto(
          carYardService.create(CarYardRestMapper.toDomain(carYard)));
    } catch (BusinessException e) {
      log.warn("Business exception for CarYardController.postCarYard: {}. {}",
          carYard, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarYardController.postCarYard: {}. {}",
          e.getClass().getSimpleName(), carYard, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarYardController.postCarYard: {} response: {}", carYard, response);

    return response;
  }

  /**
   * Updates a car yard
   *
   * @param carYard CarYardDto
   * @throws BusinessException   when the car yard was not found
   * @throws UnexpectedException when an internal server error was thrown
   */
  @PatchMapping("/patch")
  public void patchCarYard(@Valid @RequestBody CarYardDto carYard) {
    log.info("Invoking CarYardController.patchCarYard: {}", carYard);

    try {
      carYardService.update(CarYardRestMapper.toDomain(carYard));
    } catch (BusinessException e) {
      log.warn("Business exception for CarYardController.patchCarYard: {}. {}",
          carYard, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarYardController.patchCarYard: {}. {}",
          e.getClass().getSimpleName(), carYard, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarYardController.patchCarYard: {} updated successfully", carYard);
  }

  /**
   * Deletes a car yard
   *
   * @param id ID
   * @throws BusinessException   when the car yard was not found or when the car yard has clients,
   *                             sellers or credit requests associated with
   * @throws UnexpectedException when an internal server error was thrown
   */
  @DeleteMapping("/delete")
  public void deleteCarYard(@RequestParam @Min(1) Long id) {
    log.info("Invoking CarYardController.deleteCarYard: {}", id);

    try {
      carYardService.deleteById(id);
    } catch (BusinessException e) {
      log.warn("Business exception for CarYardController.deleteCarYard: {}. {}",
          id, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarYardController.deleteCarYard: {}. {}",
          e.getClass().getSimpleName(), id, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarYardController.deleteCarYard: {} deleted successfully", id);
  }

}
