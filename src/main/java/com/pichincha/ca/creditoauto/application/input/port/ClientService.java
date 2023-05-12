package com.pichincha.ca.creditoauto.application.input.port;

import com.pichincha.ca.creditoauto.domain.Client;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name ClientService.java
 * @class_description Defines interface methods for the client service of the application layer to
 * be consumed by the input adapters
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface ClientService {

  /**
   * Retrieves a Client by its id.
   *
   * @param id ID
   * @return Client
   * @throws NotFoundException when the Client was not found
   * @see com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException
   */
  Client findById(Long id);

  /**
   * Retrieves all Clients
   *
   * @return Returns a list of Clients
   */
  List<Client> findAll();

  /**
   * Returns whether a Client with the given id exists
   *
   * @param id ID
   * @return True when exists, otherwise false
   */
  boolean existsById(Long id);

  /**
   * Creates a given Client
   *
   * @param client Client
   * @return The saved entity
   * @throws BadRequestException when the client identification already exists
   */
  Client create(Client client);

  /**
   * Create clients
   *
   * @param clients List of clients
   */
  void saveAll(List<Client> clients);
}
