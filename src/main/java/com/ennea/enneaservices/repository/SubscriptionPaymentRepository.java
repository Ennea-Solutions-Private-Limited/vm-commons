package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.SubscriptionPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriptionPaymentRepository extends CrudRepository<SubscriptionPayment, Long> {

    @Query("select sp from SubscriptionPayment sp where sp.collectionMetadata.referenceId = :referenceId")
    Optional<SubscriptionPayment> findByCollectionReferenceId(String referenceId);
}
