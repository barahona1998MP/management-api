package com.lawoffice.managementapi.service;

import com.lawoffice.managementapi.dto.CustomerDto;
import com.lawoffice.managementapi.entity.Customer;
import com.lawoffice.managementapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerDto save (CustomerDto customerDto){

        Customer customer = Customer.builder()
                .identityCard(customerDto.getIdentityCard())
                .name(customerDto.getName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
        return mapToDto(customerRepository.save(customer));
    }

    public CustomerDto update ( Integer id, CustomerDto customerDto){
        CustomerDto existingCustomerDto = findById(id);
        if(existingCustomerDto != null){

            Customer customer = Customer.builder()
                    .idCustomer(existingCustomerDto.getIdCustomer())
                    .identityCard(customerDto.getIdentityCard())
                    .name(customerDto.getName())
                    .lastName(customerDto.getLastName())
                    .email(customerDto.getEmail())
                    .phoneNumber(customerDto.getPhoneNumber())
                    .build();
            return mapToDto(customerRepository.save(customer));
        }else {
            return null;
        }
    }

    public List<CustomerDto> findAll(){
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(this::mapToDto)
                .toList();
    }

    public CustomerDto findById(Integer id){
        return customerRepository.findById(id)
                .map(this::mapToDto)
                .orElse(null);
    }

    public void deleteById(Integer id){
        customerRepository.deleteById(id);
    }

    public CustomerDto mapToDto (Customer customer){
        return CustomerDto.builder()
                .idCustomer(customer.getIdCustomer())
                .identityCard(customer.getIdentityCard())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
