package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Coupon;
import com.ennea.enneaservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CouponRepository extends CrudRepository<Coupon, Long> {

    @Query("select c from Coupon c where c.user = :user order by c.expiryDate desc, c.id desc")
    Page<Coupon> findByUser(@Param("user") User user, Pageable pageable);

    @Query("select c from Coupon c where c.user = :user and c.id = :id and c.activeStatus = true and "
           + " c.redeemed = false and (FUNCTION('DATEDIFF', c.expiryDate, CURRENT_DATE) >= 0)")
    Optional<Coupon> findUnexpiredCouponByUserAndId(@Param("user") User user, @Param("id") long id);

    @Modifying
    @Transactional
    @Query(
        "update Coupon c set c.activeStatus = false, c.modificationDateTime = :modificationDateTime where c.activeStatus = true"
        + " and c.user is null")
    void updateActiveUnassignedCouponsToInactive(@Param("modificationDateTime") LocalDateTime modificationDateTime);

    @Query("select c from Coupon c where c.user is null and c.activeStatus = true")
    List<Coupon> findAllValidUnassignedCoupons();

    @Query(
        "select count(c) from Coupon c where c.user = :user and (FUNCTION('DATEDIFF', c.expiryDate, :creationDateTime) = 7)"
        + " and c.activeStatus = true")
    int findAssignedCouponsCountByUserAndCreationDateTime(@Param("user") User user,
                                                          @Param("creationDateTime") LocalDateTime creationDateTime);

    @Modifying
    @Transactional
    @Query(
        "update Coupon c set c.user = :user, c.expiryDate = :expiryDate, c.modificationDateTime = :modificationDateTime "
        + " where c.id = :id and c.activeStatus = true")
    void updateCouponWithUser(@Param("user") User user, @Param("id") long id, @Param("expiryDate") LocalDate expiryDate,
                              @Param("modificationDateTime") LocalDateTime modificationDateTime);

}
