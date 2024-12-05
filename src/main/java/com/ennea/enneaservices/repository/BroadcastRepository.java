package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Broadcast;
import com.ennea.enneaservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BroadcastRepository extends CrudRepository<Broadcast, Long> {

    @Query("SELECT b FROM Broadcast b " +
           "WHERE b.user = :admin AND " +
           "lower(concat('', b.phoneNumber)) LIKE CONCAT('%',lower(:number),'%') ORDER BY b.id DESC")
    Page<Broadcast> findAllByUserAndPhoneNumberLike(@Param("admin") User admin, @Param("number") String query,
                                                    Pageable pageable);

    @Query(value = "SELECT b FROM Broadcast b " +
                   "WHERE b.user = :supplier AND " +
                   "(:query IS NULL OR lower(b.partyName) LIKE CONCAT('%',lower (:query),'%') OR " +
                   "lower(concat('', b.phoneNumber)) LIKE CONCAT('%',lower(:query),'%')) ORDER BY b.id DESC")
    Page<Broadcast> findAllByUserAndPartyNameOrPhoneNumber(User supplier, String query, Pageable pageable);

    Page<Broadcast> findAllByUserOrderByIdDesc(User user, Pageable pageable);

}
