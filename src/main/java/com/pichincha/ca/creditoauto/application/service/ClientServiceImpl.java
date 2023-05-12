package com.pichincha.ca.creditoauto.application.service;

import com.pichincha.ca.creditoauto.application.input.port.ClientService;
import com.pichincha.ca.creditoauto.application.output.port.ClientRepository;
import com.pichincha.ca.creditoauto.domain.Client;
import com.pichincha.ca.creditoauto.infrastructure.exception.BadRequestException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author jocamach@pichincha.com
 * @class_name ClientServiceImpl.java
 * @class_description Implements the ClientService interface
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

  private ResourceBundle resourceBundle;
  private ClientRepository clientRepository;

  @Override
  public Client findById(Long id) {
    return clientRepository.findById(id);
  }

  @Override
  public List<Client> findAll() {
    return clientRepository.findAll();
  }

  @Override
  public boolean existsById(Long id) {
    return clientRepository.existsById(id);
  }

  @Override
  @Transactional
  public Client create(Client client) {
    if (clientRepository.existsByIdentification(client.getIdentification())) {
      throw new BadRequestException(resourceBundle.getString("client.duplicate"));
    }

    return clientRepository.save(client);
  }

  @Override
  public void saveAll(List<Client> clients) {
    var set = new LinkedHashSet<>(clients);
    var db = new LinkedHashSet<>(clientRepository.findByIdentifications(
        set.stream().map(Client::getIdentification).collect(Collectors.toList())));

    set.removeAll(db);
    clientRepository.saveAll(new ArrayList<>(set));
  }
}
