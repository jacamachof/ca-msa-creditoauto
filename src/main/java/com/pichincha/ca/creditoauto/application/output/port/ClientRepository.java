package com.pichincha.ca.creditoauto.application.output.port;

import com.pichincha.ca.creditoauto.domain.Client;
import java.util.List;

/**
 * @author jocamach@pichincha.com
 * @class_name ClientRepository.java
 * @class_description Defines interface methods to consume clients from external sources regardless
 * of their inner implementation
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public interface ClientRepository {

  /**
   * Retrieves a Client by its id.
   *
   * @param id ID
   * @return Client
   * @throws NotFoundException when the client was not found
   * @see com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException
   */
  Client findById(Long id);

  /**
   * Retrieves all clients
   *
   * @return Returns a list of client
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
   * Returns whether a Client with the given name exists
   *
   * @param identification Name
   * @return True when exists, otherwise false
   */
  boolean existsByIdentification(String identification);

  /**
   * Saves a given Client
   *
   * @param client Client
   * @return The saved entity
   */
  Client save(Client client);

  /**
   * Deletes an existing Client by its id
   *
   * @param id ID
   */
  void deleteById(Long id);
}
