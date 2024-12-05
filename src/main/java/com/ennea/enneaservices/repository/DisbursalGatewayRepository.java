package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.DisbursalGateway;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DisbursalGatewayRepository extends CrudRepository<DisbursalGateway, Integer> {

    Optional<DisbursalGateway> findByName(String name);

}
