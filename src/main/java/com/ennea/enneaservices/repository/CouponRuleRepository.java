package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.CouponRule;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CouponRuleRepository extends CrudRepository<CouponRule, Long> {

    @Query(value = "select * from coupon_rule where coupon_rule.active_status = 1 order by coupon_rule.id desc limit 1", nativeQuery = true)
    Optional<CouponRule> findValidCouponRule();

    @Modifying
    @Transactional
    @Query(value = "update CouponRule cr set cr.activeStatus = false, cr.updationTimestamp = :updationTimestamp where cr.activeStatus = true")
    void updateCouponRuleToInactive(@Param("updationTimestamp") LocalDateTime updationTimestamp);

}
