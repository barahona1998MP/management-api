package com.lawoffice.managementapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Entity
@Table(name = "law_user", uniqueConstraints={
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class LawUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Column(name = "last_name")
    private String lastName;
    @NotBlank
    @NotNull
    private String username;
    @NotNull
    @NotBlank
    @Email(message = "example@example.com")
    private String email;
    @NotBlank
    @NotNull
    private String password;
    private Boolean status;
}
