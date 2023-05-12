package com.pichincha.ca.creditoauto.application.service;

import com.pichincha.ca.creditoauto.application.input.port.CarService;
import com.pichincha.ca.creditoauto.application.input.port.CarYardClientService;
import com.pichincha.ca.creditoauto.application.input.port.CreditRequestService;
import com.pichincha.ca.creditoauto.application.output.port.CarRepository;
import com.pichincha.ca.creditoauto.application.output.port.CarYardRepository;
import com.pichincha.ca.creditoauto.application.output.port.ClientRepository;
import com.pichincha.ca.creditoauto.application.output.port.CreditRequestRepository;
import com.pichincha.ca.creditoauto.application.output.port.SellerRepository;
import com.pichincha.ca.creditoauto.domain.CarYard;
import com.pichincha.ca.creditoauto.domain.CarYardClient;
import com.pichincha.ca.creditoauto.domain.Client;
import com.pichincha.ca.creditoauto.domain.CreditRequest;
import com.pichincha.ca.creditoauto.domain.Seller;
import com.pichincha.ca.creditoauto.domain.enums.CreditRequestEnum;
import com.pichincha.ca.creditoauto.infrastructure.exception.BadRequestException;
import com.pichincha.ca.creditoauto.infrastructure.exception.BusinessException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author jocamach@pichincha.com
 * @class_name CreditRequestServiceImpl.java
 * @class_description Implements the CreditRequestService interface
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Slf4j
@Service
@AllArgsConstructor
public class CreditRequestServiceImpl implements CreditRequestService {

  private ResourceBundle resourceBundle;

  private CarService carService;
  private CarYardClientService carYardClientService;

  private CarRepository carRepository;
  private ClientRepository clientRepository;
  private SellerRepository sellerRepository;
  private CarYardRepository carYardRepository;
  private CreditRequestRepository creditRequestRepository;

  @Override
  public CreditRequest findById(Long id) {
    return creditRequestRepository.findById(id);
  }

  @Override
  @Transactional
  public CreditRequest create(CreditRequest creditRequest) {
    var now = ZonedDateTime.now();

    var car = carRepository.findById(creditRequest.getCar().getId());
    var client = clientRepository.findById(creditRequest.getClient().getId());
    var seller = sellerRepository.findById(creditRequest.getSeller().getId());
    var carYard = carYardRepository.findById(creditRequest.getCarYard().getId());

    creditRequest.setCreationDate(now);
    carService.validateCreditRequests(car);
    validateClientCreditRequests(client, now.toLocalDate());
    validateSellerBelongsToCarYard(seller, carYard);

    var carYardClient = new CarYardClient();
    carYardClient.setCarYard(carYard);
    carYardClient.setClient(client);
    carYardClient.setAssignedDate(ZonedDateTime.now());

    try {
      carYardClientService.create(carYardClient);
    } catch (BusinessException e) {
      log.warn("The client {} was already assigned to the car yard {}",
          client.getId(), carYard.getId());
    }

    return creditRequestRepository.save(creditRequest);
  }

  @Override
  @Transactional
  public void update(CreditRequest creditRequest) {
    if (Objects.isNull(creditRequest.getId())) {
      throw new BadRequestException(resourceBundle.getString("creditRequest.missing"));
    }

    var row = creditRequestRepository.findById(creditRequest.getId());

    if (row.getStatus().equals(CreditRequestEnum.REGISTERED)) {
      row.setInstallments(creditRequest.getInstallments());
      row.setMonthTerms(creditRequest.getMonthTerms());
      row.setObservation(creditRequest.getObservation());
      row.setUpfrontPayment(creditRequest.getUpfrontPayment());
      row.setStatus(creditRequest.getStatus());
      creditRequestRepository.save(row);
    } else {
      throw new BadRequestException(resourceBundle.getString("creditRequest.conflictState"));
    }

  }

  private void validateClientCreditRequests(Client client, LocalDate now) {
    if (Boolean.FALSE.equals(client.getCreditApproval())) {
      throw new BadRequestException(resourceBundle.getString("creditRequest.creditApproval"));
    }

    if (Optional.ofNullable(client.getCreditRequests()).isPresent()) {
      var request = client.getCreditRequests().stream().findFirst();

      if (request.isPresent() && request.get().getCreationDate().toLocalDate().isEqual(now)) {
        throw new BadRequestException(resourceBundle.getString("creditRequest.referenceClient"));
      }
    }
  }

  private void validateSellerBelongsToCarYard(Seller seller, CarYard carYard) {
    if (!seller.getCarYard().getId().equals(carYard.getId())) {
      throw new BadRequestException(resourceBundle.getString("creditRequest.referenceSeller"));
    }
  }
}
