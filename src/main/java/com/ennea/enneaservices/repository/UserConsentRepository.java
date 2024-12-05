package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.model.UserConsent;
import com.ennea.enneaservices.model.UserConsentType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserConsentRepository extends CrudRepository<UserConsent, Long> {

    Optional<UserConsent> findFirstByUserAndUserConsentType(User user, UserConsentType type);

    boolean existsByUserAndUserConsentType(User user, UserConsentType type);

}
