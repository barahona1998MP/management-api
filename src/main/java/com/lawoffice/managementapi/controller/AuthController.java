package com.lawoffice.managementapi.controller;

import com.lawoffice.managementapi.dto.LawUserDto;
import com.lawoffice.managementapi.dto.Message;
import com.lawoffice.managementapi.dto.RegisterDto;
import com.lawoffice.managementapi.entity.LawUser;
import com.lawoffice.managementapi.entity.Role;
import com.lawoffice.managementapi.repository.LawUserRepository;
import com.lawoffice.managementapi.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    /* Authentication */
    private AuthenticationManager authenticationManager;
    private LawUserRepository lawUserRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, LawUserRepository lawUserRepository, RoleRepository repository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.lawUserRepository = lawUserRepository;
        this.roleRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Message> register(@RequestBody LawUserDto lawUserDto) {
        if (lawUserRepository.existsByUsername(lawUserDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("username is taken!"));
        }
        Role roles = roleRepository.findByName("USER").get();
        LawUser lawUser = LawUser.builder()
                .name(lawUserDto.getName())
                .lastName(lawUserDto.getLastName())
                .username(lawUserDto.getUsername())
                .password(passwordEncoder.encode(lawUserDto.getPassword()))
                .email(lawUserDto.getEmail())
                .status(lawUserDto.getStatus())
                .roles(Collections.singleton(roles))
                .build();
        lawUserRepository.save(lawUser);

        return ResponseEntity.status(HttpStatus.OK).body(new Message("user registered success"));

    }
}
