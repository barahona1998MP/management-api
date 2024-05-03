package com.lawoffice.managementapi.service;

import com.lawoffice.managementapi.dto.CasesDto;

import com.lawoffice.managementapi.dto.CasesResponseDto;
import com.lawoffice.managementapi.entity.Cases;
import com.lawoffice.managementapi.entity.Customer;
import com.lawoffice.managementapi.entity.ServiceType;
import com.lawoffice.managementapi.entity.StatusCase;
import com.lawoffice.managementapi.repository.CasesRepository;
import com.lawoffice.managementapi.repository.CustomerRepository;
import com.lawoffice.managementapi.repository.ServiceTypeRepository;
import com.lawoffice.managementapi.repository.StatusCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CasesService {

    private final CasesRepository casesRepository;
    private final CustomerRepository customerRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final StatusCaseRepository statusCaseRepository;

    public CasesResponseDto save(CasesDto casesDto) {
        Customer customer = customerRepository.findById(casesDto.getIdCustomer())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + casesDto.getIdCustomer()));

        ServiceType serviceType = serviceTypeRepository.findById(casesDto.getIdService())
                .orElseThrow(() -> new IllegalArgumentException("ServiceType not found with id: " + casesDto.getIdService()));

        StatusCase statusCase = statusCaseRepository.findById(casesDto.getIdStatus())
                .orElseThrow(() -> new IllegalArgumentException("StatusCase not found with id: " + casesDto.getIdStatus()));

        Cases cases = Cases.builder()
                .customer(customer)
                .serviceType(serviceType)
                .statusCase(statusCase)
                .dateOpening(LocalDate.now())
                .build();

        return mapToDto(casesRepository.save(cases));
    }

    public CasesResponseDto update(Integer id, CasesDto casesDto){
        CasesResponseDto existingCaseDto = findById(id);

        if(existingCaseDto != null){
            Customer customer = customerRepository.findById(casesDto.getIdCustomer())
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + casesDto.getIdCustomer()));

            ServiceType serviceType = serviceTypeRepository.findById(casesDto.getIdService())
                    .orElseThrow(() -> new IllegalArgumentException("ServiceType not found with id: " + casesDto.getIdService()));

            StatusCase statusCase = statusCaseRepository.findById(casesDto.getIdStatus())
                    .orElseThrow(() -> new IllegalArgumentException("StatusCase not found with id: " + casesDto.getIdStatus()));

            Cases cases = Cases.builder()
                    .customer(customer)
                    .serviceType(serviceType)
                    .statusCase(statusCase)
                    .dateOpening(existingCaseDto.getDateOpening())
                    .build();
            return  mapToDto(casesRepository.save(cases));
        }else{
            return null;
        }
    }

    public List<CasesResponseDto> findAll(){
        return StreamSupport.stream(casesRepository.findAll().spliterator(),false)
                .map(this::mapToDto)
                .toList();
    }

    public CasesResponseDto findById(Integer id){
        return casesRepository.findById(id)
                .map(this::mapToDto)
                .orElse(null);
    }

    public void deleteById(Integer id){
        casesRepository.deleteById(id);
    }

    private CasesResponseDto mapToDto(Cases cases) {
        return CasesResponseDto.builder()
                .idCases(cases.getIdCases())
                .customerName(cases.getCustomer().getName()+" "+cases.getCustomer().getLastName())
                .serviceDescription(cases.getServiceType().getDescription())
                .statusDescription(cases.getStatusCase().getDescription())
                .dateOpening(cases.getDateOpening())
                .build();
    }

}
