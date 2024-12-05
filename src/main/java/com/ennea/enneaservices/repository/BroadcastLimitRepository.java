package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.BroadcastLimit;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BroadcastLimitRepository extends CrudRepository<BroadcastLimit, Long> {

    Optional<BroadcastLimit> findByUser(User user);

    @Query("SELECT (b.available - b.used) as rem FROM BroadcastLimit b WHERE b.user = :user")
    Integer getRemainingLimitByUser(User user);

}
