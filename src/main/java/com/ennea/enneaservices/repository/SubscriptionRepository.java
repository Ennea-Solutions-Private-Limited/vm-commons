package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

    @Query("select s from Subscription s where s.user.id = :id order by s.id desc")
    List<Subscription> getAllByUser(Long id);

    @Query("select s from Subscription s where s.user.id = :userId and s.id = :subscriptionId")
    Optional<Subscription> findByIdAndUserId(long subscriptionId, long userId);

    @Query("select s from Subscription s where s.user.id = :id and s.status =:status order by s.id desc")
    List<Subscription> getAllByUserAndStatus(Long id, String status);
}
