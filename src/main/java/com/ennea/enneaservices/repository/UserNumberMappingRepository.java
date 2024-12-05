package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.model.UserNumberMapping;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserNumberMappingRepository extends CrudRepository<UserNumberMapping, Long> {

    Boolean existsByPhoneNumber(Long phoneNumber);

    @Query("select unm from UserNumberMapping unm where unm.phoneNumber = :phoneNumber and unm.user.userStatus.status = :status")
    Optional<UserNumberMapping> findMappingByPhoneNumberAndUserStatus(long phoneNumber, String status);

    @Transactional
    @Modifying
    @Query("delete from UserNumberMapping unm where unm.user = :user and unm.phoneNumber = :number")
    void deleteByUserAndPhoneNumber(@Param("user") User user, @Param("number") Long phoneNumber);

    List<UserNumberMapping> findAllByUser(User user);

}
