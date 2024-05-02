package com.lawoffice.managementapi.controller;

import com.lawoffice.managementapi.dto.LawUserDto;
import com.lawoffice.managementapi.dto.Message;
import com.lawoffice.managementapi.entity.LawUser;
import com.lawoffice.managementapi.service.LawUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class LawUserController {

    @Autowired
    private LawUserService lawUserService;

    @GetMapping("/all")
    public ResponseEntity<List<LawUser>> findAll() {
        List<LawUser> userList = lawUserService.findAll();
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LawUser> findById(@PathVariable Integer id) {
        if (!lawUserService.existById(id))
            return new ResponseEntity(new Message("Id Not found"), HttpStatus.NOT_FOUND);
        LawUser lawUser = lawUserService.findById(id).get();
        return new ResponseEntity(lawUser, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<LawUser> findByUsername(@PathVariable String username) {
        if (!lawUserService.existsByUserName(username))
            return new ResponseEntity(new Message("username not found"), HttpStatus.NOT_FOUND);
        LawUser lawUser = lawUserService.findByUsername(username).get();
        return new ResponseEntity(lawUser, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestBody LawUserDto lawUserDto) {

        try {

            if (StringUtils.isEmpty(lawUserDto.getName()) ||
                    StringUtils.isEmpty(lawUserDto.getLastName()) ||
                    StringUtils.isEmpty(lawUserDto.getUsername()) ||
                    StringUtils.isEmpty(lawUserDto.getEmail()) ||
                    StringUtils.isEmpty(lawUserDto.getPassword())) {
                return ResponseEntity.badRequest().body(new Message("All fields are required."));
            }


            // Verificar si el correo electrónico ya está en uso
            if (lawUserService.existsByEmail(lawUserDto.getEmail())) {
                return ResponseEntity.badRequest().body(new Message("Email already in use."));
            }
            // Verificar la unicidad del nombre de usuario
            if (lawUserService.existsByUserName(lawUserDto.getUsername())){
                return ResponseEntity.badRequest().body(new Message("Username already in use."));
            }

            LawUser lawUser = LawUser.builder()
                    .name(lawUserDto.getName())
                    .lastName(lawUserDto.getLastName())
                    .username(lawUserDto.getUsername())
                    .email(lawUserDto.getEmail())
                    .password(lawUserDto.getPassword()) // Aplicar función de hash para seguridad
                    .status(lawUserDto.getStatus())
                    .build();


            lawUserService.save(lawUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message("User created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message("Error creating user: " + e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Integer id) {
        if (!lawUserService.existById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Id not found"));
        lawUserService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message("Deleted User Successfully"));
    }
}
