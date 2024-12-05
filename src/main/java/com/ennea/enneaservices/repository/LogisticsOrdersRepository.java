package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.LogisticsOrder;
import com.ennea.enneaservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LogisticsOrdersRepository extends CrudRepository<LogisticsOrder, Long> {

    Optional<LogisticsOrder> findFirstByProviderAndProviderOrderId(String provider, String orderId);

    // search param synced with logisticsOrderMapper's userName transformation
    @Query("SELECT lo FROM LogisticsOrder lo WHERE lo.source = :user AND lo.status NOT IN :excludedStatuses AND lo.destination.businessName like CONCAT('%',:search,'%') ORDER BY lo.id DESC")
    Page<LogisticsOrder> findAllBySource(User user, String search, List<String> excludedStatuses, Pageable page);

    // search param synced with logisticsOrderMapper's userName transformation
    @Query("SELECT lo FROM LogisticsOrder lo WHERE lo.destination = :user AND lo.status NOT IN :excludedStatuses AND lo.source.businessName like CONCAT('%',:search,'%') ORDER BY lo.id DESC")
    Page<LogisticsOrder> findAllByDestination(User user, String search, List<String> excludedStatuses, Pageable page);

    @Query("SELECT lo FROM LogisticsOrder lo WHERE lo.status = :status AND lo.creationDatetime <= :thresholdTime")
    List<LogisticsOrder> getLogisticsOrderByStatusAndThreshold(@Param("status") String status,
                                                               @Param("thresholdTime") LocalDateTime thresholdTime);

}
