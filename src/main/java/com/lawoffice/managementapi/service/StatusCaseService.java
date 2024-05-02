package com.lawoffice.managementapi.service;

import com.lawoffice.managementapi.dto.StatusCaseDto;
import com.lawoffice.managementapi.entity.StatusCase;
import com.lawoffice.managementapi.repository.StatusCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor

public class StatusCaseService {

    private final StatusCaseRepository statusCaseRepository;

    public StatusCaseDto save(StatusCaseDto statusCaseDto){
        StatusCase statusCase = StatusCase.builder()
                .description(statusCaseDto.getDescription())
                .build();
        return mapToDto(statusCaseRepository.save(statusCase));
    }


    public StatusCaseDto update(Integer id, StatusCaseDto statusCaseDto) {
        StatusCaseDto existingStatusCaseDto = findById(id);
        if (existingStatusCaseDto != null) {

            StatusCase updatedStatusCaseDto = StatusCase.builder()
                    .idStatus(existingStatusCaseDto.getIdStatus())
                    .description(statusCaseDto.getDescription())
                    .build();

            return mapToDto(statusCaseRepository.save(updatedStatusCaseDto));
        } else {
            return null;
        }
    }

    public List<StatusCaseDto> findAll() {
        return StreamSupport.stream(statusCaseRepository.findAll().spliterator(), false)
                .map(this::mapToDto)
                .toList();
    }

    public StatusCaseDto findById(Integer id) {
        return statusCaseRepository.findById(id)
                .map(this::mapToDto)
                .orElse(null);
    }

    public void deleteById(Integer id) {
        statusCaseRepository.deleteById(id);
    }

    private StatusCaseDto mapToDto(StatusCase statusCase) {
        return StatusCaseDto.builder()
                .idStatus(statusCase.getIdStatus())
                .description(statusCase.getDescription())
                .build();
    }
}
