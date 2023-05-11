package com.pichincha.ca.creditoauto.infrastructure.output.adapter;

import com.pichincha.ca.creditoauto.application.output.port.ClientRepository;
import com.pichincha.ca.creditoauto.domain.Client;
import com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.ClientJpaRepository;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper.ClientRepositoryMapper;
import java.util.List;
import java.util.ResourceBundle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author jocamach@pichincha.com
 * @class_name ClientRepositoryImpl.java
 * @class_description Implements the ClientRepository interface to consume clients from a relational
 * database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Repository
@AllArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

  private ResourceBundle resourceBundle;
  private ClientJpaRepository repository;

  @Override
  public Client findById(Long id) {
    return ClientRepositoryMapper.toDomain(
        repository.findByIdWithCreditRequests(id).orElseThrow(() -> {
          throw new NotFoundException(resourceBundle.getString("client.notFound"));
        }));
  }

  @Override
  public List<Client> findByCarYardId(Long id) {
    var clients = repository.findByCarYard_Id(id);

    if (clients.isEmpty()) {
      throw new NotFoundException(resourceBundle.getString("clients.notFoundByCarYard"));
    }

    return ClientRepositoryMapper.toDomainList(clients);
  }

  @Override
  public boolean existsById(Long id) {
    return repository.existsById(id);
  }

  @Override
  public boolean existsByCarYardId(Long id) {
    return repository.existsByCarYard_Id(id);
  }

  @Override
  public Client save(Client client) {
    return ClientRepositoryMapper.toDomain(
        repository.save(ClientRepositoryMapper.toEntity(client)));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
