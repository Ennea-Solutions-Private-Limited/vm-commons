package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.OtpRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OtpRequestRepository extends CrudRepository<OtpRequest, Long> {

    @Query("select otpRequest from OtpRequest otpRequest where otpRequest.sessionId = :sessionId")
    Optional<OtpRequest> findOtpRequestBySessionId(@Param("sessionId") String sessionId);

    @Query("SELECT orq FROM OtpRequest orq WHERE orq.sessionId = :sessionId AND orq.otpRequestStatus.status = :status")
    Optional<OtpRequest> findOtpRequestBySessionIdAndStatus(@Param("sessionId") String sessionId,
                                                            @Param("status") String status);

}
