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
public class CasesResponseDto {
    private Integer idCases;

    private String customerName;

    private String serviceDescription;

    private String statusDescription;

    private LocalDate dateOpening;
}
