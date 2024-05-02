package com.lawoffice.managementapi.controller;

import com.lawoffice.managementapi.dto.StatusCaseDto;
import com.lawoffice.managementapi.service.StatusCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status-cases/")
@RequiredArgsConstructor

public class StatusCaseController {

    private final StatusCaseService statusCaseService;

    @PostMapping
    public ResponseEntity<StatusCaseDto> createStatusCase(@RequestBody StatusCaseDto statusCaseDto){
        StatusCaseDto savedStatusCaseDtp = statusCaseService.save(statusCaseDto);
        return new ResponseEntity<>(savedStatusCaseDtp, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusCaseDto> updateStatusCase(@PathVariable Integer id, @RequestBody StatusCaseDto statusCaseDto) {
        StatusCaseDto updatedStatusCaseDto = statusCaseService.update(id, statusCaseDto);
        if (updatedStatusCaseDto != null) {
            return new ResponseEntity<>(updatedStatusCaseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<StatusCaseDto>> getAllStatusCases() {
        List<StatusCaseDto> statusCases = statusCaseService.findAll();
        return new ResponseEntity<>(statusCases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusCaseDto> getStatusCaseById(@PathVariable Integer id) {
        StatusCaseDto statusCaseDto = statusCaseService.findById(id);
        if (statusCaseDto != null) {
            return new ResponseEntity<>(statusCaseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatusCaseById(@PathVariable Integer id) {
        statusCaseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
