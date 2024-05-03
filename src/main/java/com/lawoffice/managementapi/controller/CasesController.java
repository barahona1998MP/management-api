package com.lawoffice.managementapi.controller;

import com.lawoffice.managementapi.dto.CasesDto;
import com.lawoffice.managementapi.dto.CasesResponseDto;
import com.lawoffice.managementapi.service.CasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cases/")
public class CasesController {
    private final CasesService casesService;

    @PostMapping
    public ResponseEntity<CasesResponseDto> createCases(@RequestBody CasesDto casesDto) {
        CasesResponseDto createdCasesResponseDtoDto = casesService.save(casesDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCasesResponseDtoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CasesResponseDto> updateCases(@PathVariable Integer id, @RequestBody CasesDto  casesDto) {
        CasesResponseDto updateCasesDto = casesService.update(id, casesDto);
        if (updateCasesDto != null) {
            return new ResponseEntity<>(updateCasesDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CasesResponseDto>> getAllCustomer() {
        List<CasesResponseDto> casesResponseDtos = casesService.findAll();
        return new ResponseEntity<>(casesResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasesResponseDto> getCasesById(@PathVariable Integer id) {
        CasesResponseDto casesResponseDto = casesService.findById(id);
        if (casesResponseDto != null) {
            return new ResponseEntity<>(casesResponseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCasesById(@PathVariable Integer id) {
        casesService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
