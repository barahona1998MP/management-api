package com.lawoffice.managementapi.repository;

import com.lawoffice.managementapi.entity.LawUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LawUserRepository extends CrudRepository<LawUser, Integer> {
    Optional<LawUser> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<LawUser> findByEmail(String email);
}
