package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.RequestStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RequestStatusRepository extends CrudRepository<RequestStatus, Integer> {

    RequestStatus findRequestStatusByStatus(String status);

    Optional<RequestStatus> findByStatus(String status);
}
