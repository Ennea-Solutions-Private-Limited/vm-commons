package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.PurchaseOrderInventoryMapping;
import com.ennea.enneaservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface PurchaseOrderInventoryMappingRepository extends CrudRepository<PurchaseOrderInventoryMapping, Long> {

    @Query(
        "select poim from PurchaseOrderInventoryMapping poim where poim.user = :user and (:query is null or lower(poim.productName) like CONCAT('%',lower(:query),'%')"
        + " or lower(poim.supplier.businessName) like CONCAT('%',lower(:query),'%'))")
    Page<PurchaseOrderInventoryMapping> findAllByUserAndProductName(User user, String query, Pageable pageable);

    @Query("select poim from PurchaseOrderInventoryMapping poim where poim.user = :user")
    List<PurchaseOrderInventoryMapping> findByUser(User user);

    @Query("select poim from PurchaseOrderInventoryMapping poim where poim.user = :user and poim.supplier = :supplier")
    List<PurchaseOrderInventoryMapping> findByUserAndSupplier(User user, User supplier);

    @Query("select poim from PurchaseOrderInventoryMapping poim where poim.user = :user and poim.supplier.id = :supplierId")
    List<PurchaseOrderInventoryMapping> findByUserAndSupplier(User user, Long supplierId);

    @Query("select poim from PurchaseOrderInventoryMapping poim where poim.user = :user and poim.id in :ids")
    List<PurchaseOrderInventoryMapping> findByUserAndIds(User user, Set<Long> ids);

    @Query("select poim from PurchaseOrderInventoryMapping poim where poim.modificationDateTime >= :date and poim.supplier is not null and poim.productCode is not null and poim.isUserCreated = true")
    List<PurchaseOrderInventoryMapping> findAllByModificationDateTimeGreaterThanEqual(LocalDateTime date);

    @Query("select poim from PurchaseOrderInventoryMapping poim where poim.user = :user and poim.productName = :productName and poim.supplier in (:suppliers)")
    List<PurchaseOrderInventoryMapping> findAllBySuppliersAndUserAndProductName(List<User> suppliers, User user,
                                                                                String productName);

    @Query("select poim from PurchaseOrderInventoryMapping poim where poim.user.id in (:users) and poim.supplier.id in (:suppliers) and concat(cast(poim.supplier.id as string), ':::', poim.productCode) in (:pairs)")
    List<PurchaseOrderInventoryMapping> findAllBySupplierIDAndProductCode(@Param("pairs") List<String> search,
                                                                          @Param("suppliers") List<Long> supplierIds,
                                                                          @Param("users") List<Long> userIds);

    @Query("select distinct poim.user from PurchaseOrderInventoryMapping poim")
    List<User> fetchDistinctUsers();

}
