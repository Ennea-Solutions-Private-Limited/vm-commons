package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.OtpRequestStatus;
import org.springframework.data.repository.CrudRepository;

public interface OtpRequestStatusRepository extends CrudRepository<OtpRequestStatus, Integer> {

    OtpRequestStatus findOtpRequestStatusByStatus(String status);

}
