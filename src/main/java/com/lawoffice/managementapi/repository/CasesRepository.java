package com.lawoffice.managementapi.repository;

import com.lawoffice.managementapi.dto.CasesDto;
import com.lawoffice.managementapi.entity.Cases;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasesRepository extends CrudRepository<Cases,Integer> {
    Cases save(CasesDto casesDto);
    Iterable<Cases> fingAll();
    void deleteById(Integer id);
}
