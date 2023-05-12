package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.impl;

import com.pichincha.ca.creditoauto.application.input.port.CarService;
import com.pichincha.ca.creditoauto.infrastructure.exception.BusinessException;
import com.pichincha.ca.creditoauto.infrastructure.exception.UnexpectedException;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.CarDto;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper.CarRestMapper;
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
 * @class_name CarController.java
 * @class_description Class that implements a REST controller for cars
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {

  private CarService carService;

  /**
   * Fetches a car by its id
   *
   * @param id ID
   * @return CarDto
   * @throws BusinessException   when the car was not found
   * @throws UnexpectedException when an internal server error was thrown
   */
  @GetMapping("/get")
  public CarDto getCar(@RequestParam @Min(1) Long id) {
    CarDto response;

    log.info("Invoking CarController.getCar: {}", id);

    try {
      response = CarRestMapper.toDto(carService.findById(id));
    } catch (BusinessException e) {
      log.warn("Business exception for CarController.getCar: {}. {}",
          id, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarController.getCar: {}. {}",
          e.getClass().getSimpleName(), id, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarController.getCar: {} response: {}", id, response);

    return response;
  }

  /**
   * Creates a car
   *
   * @param car CarDto
   * @return CardDto with id
   * @throws BusinessException   when the car plate already exists or the brand id is not valid
   * @throws UnexpectedException when an internal server error was thrown
   */
  @PostMapping("/post")
  public CarDto postCar(@Valid @RequestBody CarDto car) {
    CarDto response;

    log.info("Invoking CarController.postCar: {}", car);

    try {
      response = CarRestMapper.toDto(carService.create(CarRestMapper.toDomain(car)));
    } catch (BusinessException e) {
      log.warn("Business exception for CarController.postCar: {}. {}",
          car, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarController.postCar: {}. {}",
          e.getClass().getSimpleName(), car, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarController.postCar: {} response: {}", car, response);

    return response;
  }

  /**
   * Updates a car
   *
   * @param car CarDto
   * @throws BusinessException   when the car was not found, when the brand id is not valid, or the
   *                             new plate already exists, or the car has ongoing or dispatched
   *                             credit requests
   * @throws UnexpectedException when an internal server error was thrown
   */
  @PatchMapping("/patch")
  public void patchCar(@Valid @RequestBody CarDto car) {
    log.info("Invoking CarController.patchCar: {}", car);

    try {
      carService.update(CarRestMapper.toDomain(car));
    } catch (BusinessException e) {
      log.warn("Business exception for CarController.patchCar: {}. {}",
          car, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarController.patchCar: {}. {}",
          e.getClass().getSimpleName(), car, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarController.patchCar: {} updated successfully", car);
  }

  /**
   * Deletes a car
   *
   * @param id ID
   * @throws BusinessException   when the car id was not found, the car has ongoing or dispatched
   *                             credit requests
   * @throws UnexpectedException when an internal server error was thrown
   */
  @DeleteMapping("/delete")
  public void deleteCar(@RequestParam @Min(1) Long id) {
    log.info("Invoking CarController.deleteCar: {}", id);

    try {
      carService.deleteById(id);
    } catch (BusinessException e) {
      log.warn("Business exception for CarController.deleteCar: {}. {}",
          id, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for CarController.deleteCar: {}. {}",
          e.getClass().getSimpleName(), id, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("CarController.deleteCar: {} deleted successfully", id);
  }
}
