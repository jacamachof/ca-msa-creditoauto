package com.pichincha.ca.creditoauto.application.service;

import com.pichincha.ca.creditoauto.application.input.port.SellerService;
import com.pichincha.ca.creditoauto.application.output.port.SellerRepository;
import com.pichincha.ca.creditoauto.domain.Seller;
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
    if (sellerRepository.existsByIdentification(seller.getIdentification())) {
      throw new BadRequestException(resourceBundle.getString("seller.duplicate"));
    }

    return sellerRepository.save(seller);
  }

  @Override
  public void saveAll(List<Seller> sellers) {
    var set = new LinkedHashSet<>(sellers);
    var db = new LinkedHashSet<>(sellerRepository.findByIdentifications(
        set.stream().map(Seller::getIdentification).collect(Collectors.toList())));

    set.removeAll(db);
    sellerRepository.saveAll(new ArrayList<>(set));
  }
}
