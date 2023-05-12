package com.pichincha.ca.creditoauto.infrastructure.output.repository.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name ClientEntity.java
 * @class_description Class to persist clients in the database
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ClientEntity extends AbstractPerson {

  @Column(name = "credit_approval", nullable = false)
  private Boolean creditApproval;

  @ToString.Exclude
  @OneToMany(mappedBy = "client")
  @OrderBy("creationDate DESC")
  private List<CreditRequestEntity> creditRequests;
}
