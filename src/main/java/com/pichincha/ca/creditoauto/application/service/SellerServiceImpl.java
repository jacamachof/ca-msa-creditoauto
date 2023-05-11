package com.pichincha.ca.creditoauto.application.service;

import com.pichincha.ca.creditoauto.application.input.port.SellerService;
import com.pichincha.ca.creditoauto.application.output.port.SellerRepository;
import com.pichincha.ca.creditoauto.domain.Seller;
import com.pichincha.ca.creditoauto.infrastructure.exception.BadRequestException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author jocamach@pichincha.com
 * @class_name SellerServiceImpl.java
 * @class_description Implements the SellerService interface
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

  private ResourceBundle resourceBundle;
  private SellerRepository sellerRepository;

  @Override
  public Seller findById(Long id) {
    return sellerRepository.findById(id);
  }

  @Override
  public List<Seller> findAll() {
    return sellerRepository.findAll();
  }

  @Override
  public boolean existsById(Long id) {
    return sellerRepository.existsById(id);
  }

  @Override
  @Transactional
  public Seller create(Seller seller) {
    if ((Objects.nonNull(seller.getId()) || Objects.nonNull(seller.getIdentification()))
        && sellerRepository.existsByIdOrIdentification(seller.getId(),
        seller.getIdentification())) {
      throw new BadRequestException(resourceBundle.getString("seller.duplicate"));
    }

    return sellerRepository.save(seller);
  }
}
