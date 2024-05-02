package com.lawoffice.managementapi.repository;

import com.lawoffice.managementapi.dto.StatusCaseDto;
import com.lawoffice.managementapi.entity.StatusCase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusCaseRepository  extends CrudRepository<StatusCase,Integer> {

}
