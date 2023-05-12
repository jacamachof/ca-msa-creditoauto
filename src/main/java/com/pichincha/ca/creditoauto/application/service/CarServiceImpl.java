package com.pichincha.ca.creditoauto.application.service;

import com.pichincha.ca.creditoauto.application.input.port.CarService;
import com.pichincha.ca.creditoauto.application.output.port.BrandRepository;
import com.pichincha.ca.creditoauto.application.output.port.CarRepository;
import com.pichincha.ca.creditoauto.application.output.port.CreditRequestRepository;
import com.pichincha.ca.creditoauto.domain.Car;
import com.pichincha.ca.creditoauto.domain.enums.CreditRequestEnum;
import com.pichincha.ca.creditoauto.infrastructure.exception.BadRequestException;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author jocamach@pichincha.com
 * @class_name CarServiceImpl.java
 * @class_description Implements the CarService interface
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

  private ResourceBundle resourceBundle;
  private CarRepository carRepository;
  private BrandRepository brandRepository;
  private CreditRequestRepository creditRequestRepository;

  @Override
  public Car findById(Long id) {
    return carRepository.findById(id);
  }

  @Override
  public boolean existsById(Long id) {
    return carRepository.existsById(id);
  }

  @Override
  @Transactional
  public Car create(Car car) {
    car.setId(null);
    validateCar(car, true);
    return carRepository.save(car);
  }

  @Override
  @Transactional
  public void update(Car car) {
    if (Objects.isNull(car.getId())) {
      throw new BadRequestException(resourceBundle.getString("car.missing"));
    }

    var row = carRepository.findById(car.getId());
    validateCar(car, !car.getPlate().equals(row.getPlate()));
    validateCreditRequests(row);
    row.setPlate(car.getPlate());
    row.setModel(car.getModel());
    row.setVin(car.getVin());
    row.setBrand(car.getBrand());
    row.setType(car.getType());
    row.setCylinderCapacity(car.getCylinderCapacity());
    row.setValue(car.getValue());
    carRepository.save(row);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    var car = carRepository.findById(id);
    validateCreditRequests(car);
    creditRequestRepository.deleteById(id);
    carRepository.deleteById(id);
  }

  private void validateCar(Car car, boolean validateId) {
    if (Objects.isNull(car.getBrand().getId())) {
      throw new BadRequestException(resourceBundle.getString("brand.missingId"));
    }

    if (!brandRepository.existsById(car.getBrand().getId())) {
      throw new BadRequestException(resourceBundle.getString("brand.notFound"));
    }

    if (validateId && carRepository.existsByPlate(car.getPlate())) {
      throw new BadRequestException(resourceBundle.getString("car.duplicate"));
    }
  }

  private void validateCreditRequests(Car car) {
    if (!car.getCreditRequests().isEmpty()
        && car.getCreditRequests().get(0).getStatus() != CreditRequestEnum.CANCELED) {
      throw new BadRequestException(resourceBundle.getString("car.referenceCreditRequests"));
    }
  }
}
