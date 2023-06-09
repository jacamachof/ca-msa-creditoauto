package com.pichincha.ca.creditoauto.application.service;

import com.pichincha.ca.creditoauto.application.input.port.BrandService;
import com.pichincha.ca.creditoauto.application.output.port.BrandRepository;
import com.pichincha.ca.creditoauto.domain.Brand;
import com.pichincha.ca.creditoauto.infrastructure.exception.BadRequestException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author jocamach@pichincha.com
 * @class_name BrandServiceImpl.java
 * @class_description Implements the BrandService interface
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

  private ResourceBundle resourceBundle;
  private BrandRepository brandRepository;

  @Override
  public Brand findById(Long id) {
    return brandRepository.findById(id);
  }

  @Override
  public List<Brand> findAll() {
    return brandRepository.findAll();
  }

  @Override
  public boolean existsById(Long id) {
    return brandRepository.existsById(id);
  }

  @Override
  @Transactional
  public Brand create(Brand brand) {
    if (Objects.isNull(brand.getName())) {
      throw new BadRequestException(resourceBundle.getString("brand.missingName"));
    }

    if (brandRepository.existsByName(brand.getName())) {
      throw new BadRequestException(resourceBundle.getString("brand.duplicate"));
    }

    return brandRepository.save(brand);
  }

  @Override
  public void saveAll(List<Brand> brands) {
    var set = new LinkedHashSet<>(brands);
    var db = new LinkedHashSet<>(brandRepository.findByNames(
        set.stream().map(Brand::getName).collect(Collectors.toList())));

    set.removeAll(db);
    brandRepository.saveAll(new ArrayList<>(set));
  }
}
