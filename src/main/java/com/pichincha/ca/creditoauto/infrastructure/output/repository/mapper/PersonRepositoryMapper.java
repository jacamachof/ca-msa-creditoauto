package com.pichincha.ca.creditoauto.infrastructure.output.repository.mapper;

import com.pichincha.ca.creditoauto.domain.Person;
import com.pichincha.ca.creditoauto.domain.enums.GenderEnum;
import com.pichincha.ca.creditoauto.domain.enums.MaritalStatusEnum;
import com.pichincha.ca.creditoauto.infrastructure.output.repository.entity.AbstractPerson;
import java.util.Objects;

/**
 * @author jocamach@pichincha.com
 * @class_name PersonRepositoryMapper.java
 * @class_description Class to convert basic person information from entity objects to domain
 * objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class PersonRepositoryMapper {

  private PersonRepositoryMapper() {
    throw new AssertionError();
  }

  /**
   * The method maps the given AbstractPerson object to the given Person domain object
   *
   * @param domain Person object
   * @param entity Entity object
   */
  protected static void toDomain(Person domain, AbstractPerson entity) {
    domain.setId(entity.getId());
    domain.setIdentification(entity.getIdentification());
    domain.setFirstName(entity.getFirstName());
    domain.setLastName(entity.getLastName());
    domain.setAddress(entity.getAddress());
    domain.setLocalPhoneNumber(entity.getLocalPhoneNumber());
    domain.setMobilePhoneNumber(entity.getMobilePhoneNumber());
    domain.setBirthdate(entity.getBirthdate());

    if (Objects.nonNull(entity.getGender())) {
      domain.setGender(GenderEnum.of(entity.getGender()));
    }

    if (Objects.nonNull(entity.getMaritalStatus())) {
      domain.setMaritalStatus(MaritalStatusEnum.of(entity.getMaritalStatus()));
    }
  }

  /**
   * The method maps the given Person domain object to the given AbstractPerson object
   *
   * @param entity Entity object
   * @param domain Person object
   */
  protected static void toEntity(AbstractPerson entity, Person domain) {
    entity.setId(domain.getId());
    entity.setIdentification(domain.getIdentification());
    entity.setFirstName(domain.getFirstName());
    entity.setLastName(domain.getLastName());
    entity.setAddress(domain.getAddress());
    entity.setLocalPhoneNumber(domain.getLocalPhoneNumber());
    entity.setMobilePhoneNumber(domain.getMobilePhoneNumber());
    entity.setBirthdate(domain.getBirthdate());

    if (Objects.nonNull(domain.getGender())) {
      entity.setGender(domain.getGender().name());
    }

    if (Objects.nonNull(domain.getMaritalStatus())) {
      entity.setMaritalStatus(domain.getMaritalStatus().name());
    }
  }
}
