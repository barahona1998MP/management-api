package com.lawoffice.managementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Integer idCustomer;

    private String identityCard;

    private String name;

    private String lastName;

    private String email;

    private String phoneNumber;
}
