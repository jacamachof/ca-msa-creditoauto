package com.pichincha.ca.creditoauto.infrastructure.output.adapter;

import com.pichincha.ca.creditoauto.application.output.port.ClientRepository;
import com.pichincha.ca.creditoauto.domain.Client;
import com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.ClientJpaRepository;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper.ClientRepositoryMapper;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
        }), true);
  }

  @Override
  public List<Client> findAll() {
    return ClientRepositoryMapper.toDomainList(
        StreamSupport.stream(repository.findAll().spliterator(), false)
            .collect(Collectors.toList()), true);
  }

  @Override
  public List<Client> findByIdentifications(List<String> identifications) {
    return ClientRepositoryMapper.toDomainList(repository.findByIdentifications(identifications),
        true);
  }

  @Override
  public boolean existsById(Long id) {
    return repository.existsById(id);
  }

  @Override
  public boolean existsByIdentification(String identification) {
    return repository.existsByIdentification(identification);
  }

  @Override
  public Client save(Client client) {
    return ClientRepositoryMapper.toDomain(
        repository.save(ClientRepositoryMapper.toEntity(client)), true);
  }

  @Override
  public void saveAll(List<Client> clients) {
    repository.saveAll(ClientRepositoryMapper.toEntityList(clients));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
