package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.LogisticsOrder;
import com.ennea.enneaservices.model.LogisticsPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LogisticsPaymentRepository extends CrudRepository<LogisticsPayment, Long> {

    @Query("select lp from LogisticsPayment lp where lp.collectionMetadata.referenceId = :referenceId")
    Optional<LogisticsPayment> findByCollectionReferenceId(String referenceId);

    List<LogisticsPayment> findAllByLogisticsOrderIn(List<LogisticsOrder> orders);

}
