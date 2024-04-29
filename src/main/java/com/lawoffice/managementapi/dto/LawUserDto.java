package com.lawoffice.managementapi.dto;


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
    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Please provide a name")
    private String name;

    @NotBlank(message = "Last name cannot be blank")
    @NotNull(message = "Please provide a last name")
    private String lastName;

    @NotBlank(message = "Username cannot be blank")
    @NotNull(message = "Please provide a username")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @NotNull(message = "Please provide an email")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @NotNull(message = "Please provide a password")
    private String password;

    @NotNull(message = "Please set the status")
    private Boolean status;
}
