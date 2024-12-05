package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.TrackedLocation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface TrackedLocationRepository extends CrudRepository<TrackedLocation, Long> {

    @Query("select tl from TrackedLocation tl where tl.user.id = :userId and tl.time <= :end and tl.time >= :start order by tl.time desc")
    List<TrackedLocation> findByUserAndDateAndOrderByTime(Long userId, LocalDateTime start, LocalDateTime end);

    @Transactional
    @Modifying
    @Query("delete from TrackedLocation  tl where tl.time < :date")
    void deleteByDate(LocalDateTime date);
}
