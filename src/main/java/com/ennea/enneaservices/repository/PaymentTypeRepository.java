package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.PaymentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PaymentTypeRepository extends CrudRepository<PaymentType, Integer> {

    @Query("select p from PaymentType p where p.type = :type")
    PaymentType findByType(String type);
}