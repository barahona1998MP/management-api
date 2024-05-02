package com.lawoffice.managementapi.controller;

import com.lawoffice.managementapi.dto.ServiceTypeDto;
import com.lawoffice.managementapi.service.ServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/service-type/")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    @PostMapping
    public ResponseEntity<ServiceTypeDto> createServiceType(@RequestBody ServiceTypeDto serviceTypeDto){
        ServiceTypeDto savedStatusCaseDto = serviceTypeService.save(serviceTypeDto);
        return new ResponseEntity<>(savedStatusCaseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceTypeDto> updateServiceType(@PathVariable Integer id, @RequestBody ServiceTypeDto  serviceTypeDto) {
        ServiceTypeDto updatedServiceTypeDto = serviceTypeService.update(id, serviceTypeDto);
        if (updatedServiceTypeDto != null) {
            return new ResponseEntity<>(updatedServiceTypeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<ServiceTypeDto>> getAllServiceType() {
        List<ServiceTypeDto> serviceTypeDtos = serviceTypeService.findAll();
        return new ResponseEntity<>(serviceTypeDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeDto> getServiceTypeById(@PathVariable Integer id) {
        ServiceTypeDto serviceTypeDto = serviceTypeService.findById(id);
        if (serviceTypeDto != null) {
            return new ResponseEntity<>(serviceTypeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceTypeById(@PathVariable Integer id) {
        serviceTypeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
