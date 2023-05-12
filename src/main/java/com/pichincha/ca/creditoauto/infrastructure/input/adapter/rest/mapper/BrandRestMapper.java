package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.mapper;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.pichincha.ca.creditoauto.domain.Brand;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.BrandDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jocamach@pichincha.com
 * @class_name BrandRestMapper.java
 * @class_description Class to convert Brand DTO objects to domain objects and vice-versa
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
public class BrandRestMapper {

  private BrandRestMapper() {
    throw new AssertionError();
  }

  public static BrandDto toDto(Brand domain) {
    if (domain == null) {
      return null;
    }

    return BrandDto.builder()
        .id(domain.getId())
        .name(domain.getName())
        .build();
  }

  public static Brand toDomain(BrandDto dto) {
    if (dto == null) {
      return null;
    }

    var domain = new Brand();
    domain.setId(dto.getId());
    domain.setName(dto.getName());
    return domain;
  }

  public static List<BrandDto> toDtoList(List<Brand> domains) {
    if (domains == null) {
      return null;
    }

    var dtos = new ArrayList<BrandDto>();

    for (var domain : domains) {
      dtos.add(toDto(domain));
    }

    return dtos;
  }

  public static List<Brand> toDomainList(List<BrandDto> dtos) {
    if (dtos == null) {
      return null;
    }

    var domains = new ArrayList<Brand>();

    for (var dto : dtos) {
      domains.add(toDomain(dto));
    }

    return domains;
  }

  public static List<Brand> parseCsv(MultipartFile multipartFile) throws IOException {
    MappingIterator<BrandDto> iterator = new CsvMapper()
        .readerWithTypedSchemaFor(BrandDto.class)
        .readValues(multipartFile.getInputStream());

    return toDomainList(iterator.readAll());
  }
}
