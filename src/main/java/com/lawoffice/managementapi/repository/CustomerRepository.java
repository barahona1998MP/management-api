package com.lawoffice.managementapi.repository;

import com.lawoffice.managementapi.dto.CustomerDto;
import com.lawoffice.managementapi.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
