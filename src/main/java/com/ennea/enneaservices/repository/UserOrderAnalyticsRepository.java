package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.UserOrderAnalytics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserOrderAnalyticsRepository extends CrudRepository<UserOrderAnalytics, Long> {

    @Query("select uao from UserOrderAnalytics uao where uao.user.id in :userIds and uao.mode = :mode")
    List<UserOrderAnalytics> findAllByUsersAndMode(Set<Long> userIds, String mode);

    @Query("select uao from UserOrderAnalytics uao where uao.user.id = :userId and uao.mode = :mode")
    Optional<UserOrderAnalytics> findByUserAndMode(Long userId, String mode);
}
