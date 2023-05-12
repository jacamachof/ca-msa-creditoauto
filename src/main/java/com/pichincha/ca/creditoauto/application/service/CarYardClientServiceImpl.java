package com.pichincha.ca.creditoauto.application.service;

import com.pichincha.ca.creditoauto.application.input.port.CarYardClientService;
import com.pichincha.ca.creditoauto.application.output.port.CarYardClientRepository;
import com.pichincha.ca.creditoauto.application.output.port.CarYardRepository;
import com.pichincha.ca.creditoauto.application.output.port.ClientRepository;
import com.pichincha.ca.creditoauto.domain.CarYardClient;
import com.pichincha.ca.creditoauto.infrastructure.exception.BadRequestException;
import com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardClientServiceImpl.java
 * @class_description Implements the CarYardClientService interface
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Service
@AllArgsConstructor
public class CarYardClientServiceImpl implements CarYardClientService {

  private ResourceBundle resourceBundle;
  private CarYardClientRepository carYardClientRepository;
  private CarYardRepository carYardRepository;
  private ClientRepository clientRepository;

  @Override
  public CarYardClient findById(Long id) {
    return carYardClientRepository.findById(id);
  }

  @Override
  public boolean existsById(Long id) {
    return carYardClientRepository.existsById(id);
  }

  @Override
  @Transactional
  public CarYardClient create(CarYardClient carYardClient) {
    carYardClient.setId(null);
    validateCarYardClient(carYardClient, true);
    return carYardClientRepository.save(carYardClient);
  }

  @Override
  @Transactional
  public void update(CarYardClient carYardClient) {
    if (Objects.isNull(carYardClient.getId())) {
      throw new BadRequestException(resourceBundle.getString("carYardClient.missing"));
    }

    var row = carYardClientRepository.findById(carYardClient.getId());
    validateCarYardClient(carYardClient,
        !carYardClient.getCarYard().getId().equals(row.getCarYard().getId())
            || !carYardClient.getClient().getId().equals(row.getClient().getId()));
    row.setCarYard(carYardClient.getCarYard());
    row.setClient(carYardClient.getClient());
    row.setAssignedDate(carYardClient.getAssignedDate());
    carYardClientRepository.save(row);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    if (carYardClientRepository.existsById(id)) {
      carYardClientRepository.deleteById(id);
    } else {
      throw new NotFoundException(resourceBundle.getString("carYardClient.notFound"));
    }
  }

  private void validateCarYardClient(CarYardClient carYardClient, boolean validateId) {
    if (Objects.isNull(carYardClient.getCarYard().getId())) {
      throw new BadRequestException(resourceBundle.getString("carYard.missing"));
    }

    if (Objects.isNull(carYardClient.getClient().getId())) {
      throw new BadRequestException(resourceBundle.getString("client.missing"));
    }

    if (!carYardRepository.existsById(carYardClient.getCarYard().getId())) {
      throw new BadRequestException(resourceBundle.getString("carYard.notFound"));
    }

    if (!clientRepository.existsById(carYardClient.getClient().getId())) {
      throw new BadRequestException(resourceBundle.getString("client.notFound"));
    }

    if (validateId && carYardClientRepository.existsByCarYardIdAndClientId(
        carYardClient.getCarYard().getId(), carYardClient.getClient().getId())) {
      throw new BadRequestException(resourceBundle.getString("carYardClient.duplicate"));
    }
  }
}
