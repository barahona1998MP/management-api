package com.lawoffice.managementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CasesDto {
    private Integer idCases;

    private Integer idCustomer;

    private Integer idService;

    private Integer idStatus;

    private LocalDate dateOpening;
}
