package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.DisbursalStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DisbursalStatusRepository extends CrudRepository<DisbursalStatus, Integer> {

    Optional<DisbursalStatus> findByName(String name);

}
