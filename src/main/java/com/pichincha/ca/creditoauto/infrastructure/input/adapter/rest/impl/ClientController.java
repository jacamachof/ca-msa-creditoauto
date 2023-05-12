package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.impl;

import com.pichincha.ca.creditoauto.application.input.port.ClientService;
import com.pichincha.ca.creditoauto.infrastructure.exception.BusinessException;
import com.pichincha.ca.creditoauto.infrastructure.exception.UnexpectedException;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.bean.ValidationService;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.ClientDto;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper.ClientRestMapper;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * @class_name ClientController.java
 * @class_description Class that implements a REST controller for clients
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

  private ClientService clientService;
  private ValidationService validationService;

  /**
   * Fetches all clients
   *
   * @return List(ClientDto) Returns a list of clients
   * @throws UnexpectedException when an internal server error was thrown
   */
  @GetMapping("/getAll")
  public List<ClientDto> getClients() {
    List<ClientDto> response;

    log.info("Invoking ClientController.getClients");

    try {
      response = ClientRestMapper.toDtoList(clientService.findAll());
    } catch (Exception e) {
      log.error("Exception {} thrown for ClientController.getClients. {}",
          e.getClass().getSimpleName(), e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("ClientController.getClients response: {}", response);

    return response;
  }

  /**
   * Creates a client
   *
   * @param client ClientDto
   * @return ClientDto with id
   * @throws BusinessException   when the client identification or ID already exist
   * @throws UnexpectedException when an internal server error was thrown
   */
  @PostMapping("/post")
  public ClientDto postClient(@Valid @RequestBody ClientDto client) {
    ClientDto response;

    log.info("Invoking ClientController.postClient: {}", client);

    try {
      response = ClientRestMapper.toDto(
          clientService.create(ClientRestMapper.toDomain(client)));
    } catch (BusinessException e) {
      log.warn("Business exception for ClientController.postClient: {}. {}",
          client, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("Exception {} thrown for ClientController.postClient: {}. {}",
          e.getClass().getSimpleName(), client, e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("ClientController.postClient: {} response: {}", client, response);

    return response;
  }

  @PostMapping("/csv")
  public void postCsv(@RequestParam MultipartFile file) {
    log.info("Invoking ClientController.postCsv");

    var dtos = parseCsv(file);
    validationService.validateClients(dtos);

    try {
      clientService.saveAll(ClientRestMapper.toDomainList(dtos));
    } catch (Exception e) {
      log.error("Exception {} thrown for ClientController.postCsv. {}",
          e.getClass().getSimpleName(), e.getMessage(), e);
      throw new UnexpectedException();
    }

    log.info("ClientController.postCsv success");
  }

  private List<ClientDto> parseCsv(MultipartFile file) {
    List<ClientDto> dtos;

    try {
      dtos = ClientRestMapper.parseCsv(file);
    } catch (IOException e) {
      log.warn("IOException for ClientController.postCsv: The CSV file format is not correct. {}",
          e.getMessage(), e);
      throw new BusinessException("The CSV file format is not correct");
    }

    return dtos;
  }
}