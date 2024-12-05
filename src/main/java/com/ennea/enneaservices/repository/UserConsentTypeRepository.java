package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.UserConsentType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserConsentTypeRepository extends CrudRepository<UserConsentType, Long> {

    Optional<UserConsentType> findFirstByType(String type);


}
