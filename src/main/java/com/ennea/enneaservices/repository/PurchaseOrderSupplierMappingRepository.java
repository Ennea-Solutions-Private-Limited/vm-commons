package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.PurchaseOrderSupplierMapping;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface PurchaseOrderSupplierMappingRepository extends CrudRepository<PurchaseOrderSupplierMapping, Long> {

    @Query("select p from PurchaseOrderSupplierMapping p where p.user = :user")
    List<PurchaseOrderSupplierMapping> findByUser(User user);

    @Query("select p from PurchaseOrderSupplierMapping p where p.user =:customer and p.supplier = :supplier")
    List<PurchaseOrderSupplierMapping> findByUserAndSupplier(User customer, User supplier);

    @Query("select p from PurchaseOrderSupplierMapping p where p.id in :ids and p.user = :user")
    List<PurchaseOrderSupplierMapping> findPurchaseOrderSupplierMappingByUserAndIds(User user, Set<Long> ids);

}
