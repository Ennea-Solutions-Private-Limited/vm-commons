package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.CouponSummary;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CouponSummaryRepository extends CrudRepository<CouponSummary, Long> {

    Optional<CouponSummary> findByUser(User user);

    @Query("select cs from CouponSummary cs where cs.user.id in :userIds")
    List<CouponSummary> findAllByUserIds(Set<Long> userIds);
}
