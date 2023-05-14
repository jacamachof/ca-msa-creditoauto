package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.impl;

import com.pichincha.ca.creditoauto.application.input.port.BrandService;
import com.pichincha.ca.creditoauto.infrastructure.exception.BusinessException;
import com.pichincha.ca.creditoauto.infrastructure.exception.UnexpectedException;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.BrandDto;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper.BrandRestMapper;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
 * @class_name BrandController.java
 * @class_description Class that implements a REST controller for brands
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/brands",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BrandController {

  private BrandService brandService;

  /**
   * Fetches all brands
   *
   * @return List(BrandDto) Returns a list of brands
   * @throws UnexpectedException when an internal server error was thrown
   */
  @GetMapping("/getAll")
  public ResponseEntity<List<BrandDto>> getBrands() {
    List<BrandDto> response;

    log.info("Invoking BrandController.getBrands");

    try {
      response = BrandRestMapper.toDtoList(brandService.findAll());
    } catch (Exception e) {
      log.error("Exception {} thrown for BrandController.getBrands. {}",
          e.getClass().getSimpleName(), e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("BrandController.getCarYard response: {}", response);

    return ResponseEntity.ok(response);
  }

  /**
   * Creates a brand
   *
   * @param brand BrandDto
   * @return BrandDto with id
   * @throws BusinessException   when the brand name or ID already exist
   * @throws UnexpectedException when an internal server error was thrown
   */
  @PostMapping("/post")
  public ResponseEntity<BrandDto> postBrand(@Valid @RequestBody BrandDto brand) {
    BrandDto response;

    log.info("Invoking BrandController.postBrand: {}", brand);

    try {
      response = BrandRestMapper.toDto(
          brandService.create(BrandRestMapper.toDomain(brand)));
    } catch (BusinessException e) {
      log.warn("Business exception for BrandController.postBrand: {}. {}",
          brand, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for BrandController.postBrand: {}. {}",
          e.getClass().getSimpleName(), brand, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("BrandController.postBrand: {} response: {}", brand, response);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/csv")
  public ResponseEntity<Void> postCsv(@RequestParam MultipartFile file) {
    log.info("Invoking BrandController.postCsv");

    try {
      brandService.saveAll(BrandRestMapper.parseCsv(file));
    } catch (IOException e) {
      log.warn("IOException for BrandController.postCsv: The CSV file format is not correct. {}",
          e.getMessage(), e);
      throw new BusinessException("The CSV file format is not correct");
    } catch (DataIntegrityViolationException e) {
      log.warn("DataIntegrityViolationException {} thrown for BrandController.postCsv. {}",
          e.getClass().getSimpleName(), e.getMessage(), e);
      throw new BusinessException("One or more rows are invalid");
    } catch (Exception e) {
      log.error("Exception {} thrown for BrandController.postCsv. {}",
          e.getClass().getSimpleName(), e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("BrandController.postCsv success");

    return ResponseEntity.ok().build();
  }
}
