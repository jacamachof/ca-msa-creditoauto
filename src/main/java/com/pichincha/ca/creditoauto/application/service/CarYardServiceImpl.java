package com.pichincha.ca.creditoauto.application.service;

import com.pichincha.ca.creditoauto.application.input.port.CarYardService;
import com.pichincha.ca.creditoauto.application.output.port.CarYardRepository;
import com.pichincha.ca.creditoauto.application.output.port.ClientRepository;
import com.pichincha.ca.creditoauto.application.output.port.CreditRequestRepository;
import com.pichincha.ca.creditoauto.application.output.port.SellerRepository;
import com.pichincha.ca.creditoauto.domain.CarYard;
import com.pichincha.ca.creditoauto.infrastructure.exception.BadRequestException;
import com.pichincha.ca.creditoauto.infrastructure.exception.NotFoundException;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author jocamach@pichincha.com
 * @class_name CarYardServiceImpl.java
 * @class_description Implements the CarYardService interface
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Service
@AllArgsConstructor
public class CarYardServiceImpl implements CarYardService {

  private ResourceBundle resourceBundle;
  private CarYardRepository carYardRepository;
  private ClientRepository clientRepository;
  private SellerRepository sellerRepository;
  private CreditRequestRepository creditRequestRepository;

  @Override
  public CarYard findById(Long id) {
    return carYardRepository.findById(id);
  }

  @Override
  public CarYard findCarYardClients(Long id) {
    var response = carYardRepository.findByIdWithClients(id);

    if (response.getClients().isEmpty()) {
      throw new NotFoundException(resourceBundle.getString("carYard.notFoundClients"));
    }

    return response;
  }

  @Override
  public CarYard findCarYardSellers(Long id) {
    var response = carYardRepository.findByIdWithSellers(id);

    if (response.getSellers().isEmpty()) {
      throw new NotFoundException(resourceBundle.getString("carYard.notFoundSellers"));
    }

    return response;
  }

  @Override
  public boolean existsById(Long id) {
    return carYardRepository.existsById(id);
  }

  @Override
  @Transactional
  public CarYard create(CarYard carYard) {
    carYard.setId(null);
    return carYardRepository.save(carYard);
  }

  @Override
  @Transactional
  public void update(CarYard carYard) {
    if (Objects.isNull(carYard.getId())) {
      throw new BadRequestException(resourceBundle.getString("carYard.missing"));
    }

    var row = carYardRepository.findById(carYard.getId());
    row.setName(carYard.getName());
    row.setAddress(carYard.getAddress());
    row.setPhoneNumber(carYard.getPhoneNumber());
    row.setPointOfSale(carYard.getPointOfSale());
    carYardRepository.save(row);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    if (carYardRepository.existsById(id)) {
      if (clientRepository.existsByCarYardId(id)) {
        throw new BadRequestException(resourceBundle.getString("carYard.referenceClients"));
      }

      if (sellerRepository.existsByCarYardId(id)) {
        throw new BadRequestException(resourceBundle.getString("carYard.referenceSellers"));
      }

      if (creditRequestRepository.existsByCarYardId(id)) {
        throw new BadRequestException(resourceBundle.getString("carYard.referenceCreditRequests"));
      }

      carYardRepository.deleteById(id);
    } else {
      throw new NotFoundException(resourceBundle.getString("carYard.notFound"));
    }
  }
}
