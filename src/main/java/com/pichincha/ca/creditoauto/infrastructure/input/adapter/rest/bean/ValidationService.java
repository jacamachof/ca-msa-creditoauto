package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.bean;

import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.ClientDto;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto.SellerDto;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class ValidationService {

  public void validateSellers(List<@Valid SellerDto> sellers) {
    // Empty for validation purposes
  }

  public void validateClients(List<@Valid ClientDto> clients) {
    // Empty for validation purposes
  }
}
