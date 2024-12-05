package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.CouponCase;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface CouponCaseRepository extends CrudRepository<CouponCase, Long> {

    @Query("select cs from CouponCase cs where cs.activeStatus = true")
    List<CouponCase> findValidCouponCases();

    @Modifying
    @Transactional
    @Query(value = "update CouponCase cs set cs.activeStatus = false, cs.updationTimestamp = :updationTimestamp where cs.activeStatus = true")
    void updateCouponCasesToInactive(@Param("updationTimestamp") LocalDateTime updationTimestamp);

}
