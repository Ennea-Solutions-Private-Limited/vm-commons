package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.PurchaseStats;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

public interface PurchaseStatsRepository extends CrudRepository<PurchaseStats, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM PurchaseStats ps WHERE ps.user = :user AND ps.transactionDate IN :dates")
    void deleteAllByUserAndDates(User user, Set<LocalDate> dates);

}
