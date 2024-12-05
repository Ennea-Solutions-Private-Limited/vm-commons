package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.TransactionMode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionModeRepository extends CrudRepository<TransactionMode, Integer> {

    Optional<TransactionMode> findByName(String name);

}