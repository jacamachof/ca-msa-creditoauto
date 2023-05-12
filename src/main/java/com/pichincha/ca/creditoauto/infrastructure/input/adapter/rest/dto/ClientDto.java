package com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.bean.GenderValidation;
import com.pichincha.ca.creditoauto.infrastructure.input.adapter.rest.bean.MaritalStatusValidation;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jocamach@pichincha.com
 * @class_name ClientDto.java
 * @class_description Contains client information
 * @create_date 10/05/2023 Copyright 2023 Banco Pichincha.
 */
@Builder
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "identification", "firstName", "lastName", "address", "localPhoneNumber",
    "mobilePhoneNumber", "birthdate", "gender", "maritalStatus", "creditApproval"})
public class ClientDto {

  @Min(1)
  private Long id;

  @NotBlank
  @Size(min = 1, max = 20)
  private String identification;

  @NotBlank
  @Size(min = 1, max = 255)
  private String firstName;

  @NotBlank
  @Size(min = 1, max = 255)
  private String lastName;

  @NotBlank
  @Size(min = 1, max = 512)
  private String address;

  @NotBlank
  @Size(min = 1, max = 20)
  @Pattern(regexp = "^[0-9]*$")
  private String localPhoneNumber;

  @NotBlank
  @Size(min = 1, max = 20)
  @Pattern(regexp = "^[0-9]*$")
  private String mobilePhoneNumber;

  @NotNull
  @Past
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthdate;

  @NotBlank
  @GenderValidation()
  private String gender;

  @NotBlank
  @MaritalStatusValidation()
  private String maritalStatus;

  @NotNull
  private Boolean creditApproval;

  @ToString.Exclude
  private List<CreditRequestDto> creditRequests;
}
