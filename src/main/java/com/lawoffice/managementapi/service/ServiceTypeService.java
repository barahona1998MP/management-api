package com.lawoffice.managementapi.service;

import com.lawoffice.managementapi.dto.ServiceTypeDto;


import com.lawoffice.managementapi.entity.ServiceType;
import com.lawoffice.managementapi.repository.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;


    public ServiceTypeDto save(ServiceTypeDto serviceTypeDto){
        ServiceType serviceType = ServiceType.builder()
                .idService(serviceTypeDto.getIdService())
                .description(serviceTypeDto.getDescription())
                .status(Boolean.TRUE)
                .build();
                return mapToDto(serviceTypeRepository.save(serviceType));
    }

    public ServiceTypeDto update(Integer id, ServiceTypeDto serviceTypeDto) {
        ServiceTypeDto existingServiceTypeDto= findById(id);
        if (existingServiceTypeDto != null) {

            ServiceType updatedServiceType = ServiceType.builder()
                    .idService(existingServiceTypeDto.getIdService())
                    .description(serviceTypeDto.getDescription())
                    .status(serviceTypeDto.getStatus())
                    .build();

            return mapToDto(serviceTypeRepository.save(updatedServiceType));
        } else {
            return null;
        }
    }


    public List<ServiceTypeDto> findAll() {
        return StreamSupport.stream(serviceTypeRepository.findAll().spliterator(), false)
                .map(this::mapToDto)
                .toList();
    }

    public ServiceTypeDto findById(Integer id) {
        return serviceTypeRepository.findById(id)
                .map(this::mapToDto)
                .orElse(null);
    }

    public void deleteById(Integer id){
        serviceTypeRepository.deleteById(id);
    }


    public ServiceTypeDto mapToDto(ServiceType serviceType){
        return ServiceTypeDto.builder()
                .idService(serviceType.getIdService())
                .description(serviceType.getDescription())
                .status(serviceType.getStatus())
                .build();
    }
}
