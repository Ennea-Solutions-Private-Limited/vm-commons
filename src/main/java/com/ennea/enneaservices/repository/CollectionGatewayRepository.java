package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.CollectionGateway;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CollectionGatewayRepository extends CrudRepository<CollectionGateway, Integer> {

    Optional<CollectionGateway> findByName(String name);

}
