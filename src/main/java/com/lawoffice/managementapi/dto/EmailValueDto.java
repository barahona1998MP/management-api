package com.lawoffice.managementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailValueDto {
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String userName;
    private String jwt;
}
