package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.NotificationAvoidance;
import jakarta.annotation.Nonnull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotificationAvoidanceRepository extends CrudRepository<NotificationAvoidance, Long> {

    @Nonnull
    Optional<NotificationAvoidance> findByPhoneNumber(@Nonnull Long phoneNumber);

    @Nonnull
    Optional<NotificationAvoidance> findByPhoneNumberAndIsActiveTrue(@Nonnull Long phoneNumber);

}
