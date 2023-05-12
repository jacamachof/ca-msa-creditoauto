package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.impl;

import com.pichincha.ca.creditoauto.application.input.port.SellerService;
import com.pichincha.ca.creditoauto.infrastructure.exception.BusinessException;
import com.pichincha.ca.creditoauto.infrastructure.exception.UnexpectedException;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.bean.ValidationService;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.SellerDto;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper.SellerRestMapper;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

  private Validator validator;
  private SellerService sellerService;
  private ValidationService validationService;

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

  @PostMapping("/csv")
  public void postCsv(@RequestParam MultipartFile file) {
    log.info("Invoking SellerController.postCsv");

    var dtos = parseCsv(file);
    validationService.validateSellers(dtos);

    try {
      sellerService.saveAll(SellerRestMapper.toDomainList(dtos));
    } catch (DataIntegrityViolationException e) {
      log.warn("DataIntegrityViolationException {} thrown for SellerController.postCsv. {}",
          e.getClass().getSimpleName(), e.getMessage(), e);
      throw new BusinessException("One or more rows are invalid");
    } catch (Exception e) {
      log.error("Exception {} thrown for SellerController.postCsv. {}",
          e.getClass().getSimpleName(), e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("SellerController.postCsv success");
  }

  private List<SellerDto> parseCsv(MultipartFile file) {
    List<SellerDto> dtos;

    try {
      dtos = SellerRestMapper.parseCsv(file);
    } catch (IOException e) {
      log.warn("IOException for SellerController.postCsv: The CSV file format is not correct. {}",
          e.getMessage(), e);
      throw new BusinessException("The CSV file format is not correct");
    }

    return dtos;
  }
}
