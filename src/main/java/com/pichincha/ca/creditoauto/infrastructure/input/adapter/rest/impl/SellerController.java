package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.impl;

import com.pichincha.ca.creditoauto.application.input.port.SellerService;
import com.pichincha.ca.creditoauto.infrastructure.exception.BusinessException;
import com.pichincha.ca.creditoauto.infrastructure.exception.UnexpectedException;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.SellerDto;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper.SellerRestMapper;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jocamach@pichincha.com
 * @class_name SellerController.java
 * @class_description Class that implements a REST controller for sellers
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sellers")
@AllArgsConstructor
public class SellerController {

  private SellerService sellerService;

  /**
   * Fetches all sellers
   *
   * @return List(SellerDto) Returns a list of sellers
   * @throws UnexpectedException when an internal server error was thrown
   */
  @GetMapping("/getAll")
  public List<SellerDto> getSellers() {
    List<SellerDto> response;

    log.info("Invoking SellerController.getSellers");

    try {
      response = SellerRestMapper.toDtoList(sellerService.findAll());
    } catch (Exception e) {
      log.error("Exception {} thrown for SellerController.getSellers. {}",
          e.getClass().getSimpleName(), e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("SellerController.getSellers response: {}", response);

    return response;
  }

  /**
   * Creates a seller
   *
   * @param seller SellerDto
   * @return SellerDto with id
   * @throws BusinessException   when the seller identification or ID already exist
   * @throws UnexpectedException when an internal server error was thrown
   */
  @PostMapping("/post")
  public SellerDto postSeller(@Valid @RequestBody SellerDto seller) {
    SellerDto response;

    log.info("Invoking SellerController.postSeller: {}", seller);

    try {
      response = SellerRestMapper.toDto(
          sellerService.create(SellerRestMapper.toDomain(seller)));
    } catch (BusinessException e) {
      log.warn("Business exception for SellerController.postSeller: {}. {}",
          seller, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for SellerController.postSeller: {}. {}",
          e.getClass().getSimpleName(), seller, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("SellerController.postSeller: {} response: {}", seller, response);

    return response;
  }
}
