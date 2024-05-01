package com.lawoffice.managementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusCaseDto {
    private Integer idStatus;

    private String description;
}
