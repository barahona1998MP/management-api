package com.lawoffice.managementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceTypeDto {
    private Integer idService;

    private String description;

    private Boolean status;
}
