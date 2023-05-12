package com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper;

import com.pichincha.ca.creditoauto.domain.Client;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.ClientEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Persistence;

/**
 * @author jocamach@pichincha.com
 * @class_name ClientRepositoryMapper.java
 * @class_description Class to convert Client entity objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class ClientRepositoryMapper {

  private ClientRepositoryMapper() {
    throw new AssertionError();
  }

  /**
   * The method converts the given Client entity object to Client domain object
   *
   * @param entity ClientEntity object
   * @return Client Returns Client object
   */
  public static Client toDomain(ClientEntity entity) {
    if (entity == null) {
      return null;
    }

    var domain = new Client();

    PersonRepositoryMapper.toDomain(domain, entity);
    domain.setCreditApproval(entity.getCreditApproval());

    if (Objects.nonNull(entity.getCreditRequests()) &&
        Persistence.getPersistenceUtil().isLoaded(entity, "creditRequests")) {
      domain.setCreditRequests(
          CreditRequestRepositoryMapper.toDomainList(entity.getCreditRequests()));
    }

    return domain;
  }

  /**
   * The method converts the given list of Client entity objects to a list of Client domain objects
   *
   * @param entities List of ClientEntity objects
   * @return List(Client) Returns a list of Client objects
   */
  public static List<Client> toDomainList(List<ClientEntity> entities) {
    if (entities == null) {
      return null;
    }

    var domains = new ArrayList<Client>();

    for (var entity : entities) {
      domains.add(toDomain(entity));
    }

    return domains;
  }

  /**
   * The method converts the given Client domain object to Client entity object
   *
   * @param domain Client object
   * @return ClientEntity Returns ClientEntity object
   */
  public static ClientEntity toEntity(Client domain) {
    if (domain == null) {
      return null;
    }

    var entity = new ClientEntity();

    PersonRepositoryMapper.toEntity(entity, domain);
    entity.setCreditApproval(domain.getCreditApproval());

    return entity;
  }

  /**
   * The method converts the given list of Client domain objects to a list of Client entity objects
   *
   * @param domains List of Client objects
   * @return List(ClientEntity) Returns a list of ClientEntity objects
   */
  public static List<ClientEntity> toEntityList(List<Client> domains) {
    if (domains == null) {
      return null;
    }

    var entities = new ArrayList<ClientEntity>();

    for (var domain : domains) {
      entities.add(toEntity(domain));
    }

    return entities;
  }
}
