package com.lawoffice.managementapi.repository;

import com.lawoffice.managementapi.dto.ServiceTypeDto;
import com.lawoffice.managementapi.entity.ServiceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends CrudRepository<ServiceType,Integer> {

}
