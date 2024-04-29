package com.lawoffice.managementapi.controller;

import com.lawoffice.managementapi.dto.LawUserDto;
import com.lawoffice.managementapi.dto.Message;
import com.lawoffice.managementapi.entity.LawUser;
import com.lawoffice.managementapi.service.LawUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class LawUserController {

    @Autowired
    LawUserService lawUserService;

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
        if (!lawUserService.existByUsername(username))
            return new ResponseEntity(new Message("username not found"), HttpStatus.NOT_FOUND);
        LawUser lawUser = lawUserService.findByUsername(username).get();
        return new ResponseEntity(lawUser, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid LawUserDto lawUserDto) {
        if (lawUserDto == null)
            return new ResponseEntity(new Message("There cannot be empty or null fields or variables."), HttpStatus.BAD_REQUEST);
        LawUser lawUser = LawUser.builder()
                .email(lawUserDto.getEmail())
                .name(lawUserDto.getName())
                .lastName(lawUserDto.getLastName())
                .username(lawUserDto.getUsername())
                .password(lawUserDto.getPassword())
                .status(lawUserDto.getStatus())
                .build();
        lawUserService.save(lawUser);
        return new ResponseEntity(new Message("User create successfully"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (!lawUserService.existById(id))
            return new ResponseEntity(new Message("Id not found"), HttpStatus.NOT_FOUND);
        lawUserService.delete(id);
        return new ResponseEntity(new Message("Deleted User Successfully"), HttpStatus.NO_CONTENT);
    }
}
