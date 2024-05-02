package com.lawoffice.managementapi.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LawUserDto {
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Boolean status;
}
