package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.CollectionStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CollectionStatusRepository extends CrudRepository<CollectionStatus, Integer> {

    Optional<CollectionStatus> findByName(String name);

}
