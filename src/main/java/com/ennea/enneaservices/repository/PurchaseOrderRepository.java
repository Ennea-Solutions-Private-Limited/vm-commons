package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.model.purchaseorderV2.PurchaseOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {

    @Query("select po from PurchaseOrder po where po.customer = :customer")
    Optional<PurchaseOrder> findByCustomer(User customer);
}
