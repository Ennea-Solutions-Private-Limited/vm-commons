package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.InventoryAvailability;
import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.model.projection.InventoryAvailabilityView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface InventoryAvailabilityRepository extends CrudRepository<InventoryAvailability, Long> {

    @Query("select ia.id as id, ia.searchPrefixes as search from InventoryAvailability ia")
    Page<InventoryIdAndSearchPrefix> getInventory(Pageable pageable);

    @Query("select ia from InventoryAvailability  ia where ia.id in :inventoryIds and ia.activeStatus = :activeStatus and ia.hiddenStatus = :hiddenStatus")
    List<InventoryAvailability> findAllByIdsAndActiveStatusAndHiddenStatus(List<Long> inventoryIds,
                                                                           boolean activeStatus, boolean hiddenStatus);

    @Query(
        "select ia from InventoryAvailability ia where ia.supplier.id in (:supplierIds) and upper(ia.searchPrefixes) like CONCAT('%',upper(:name),'%')"
        + " and ia.activeStatus = true and ia.hiddenStatus = false")
    List<InventoryAvailability> findDistinctByProductNameAndSuppliers(@Param("name") String name,
                                                                      @Param("supplierIds") List<Long> supplierIds);

    @Query(
        "select ia from InventoryAvailability ia where ia.supplier is null and upper(ia.searchPrefixes) like CONCAT('%',upper(:name),'%')"
        + " and ia.activeStatus = true and ia.hiddenStatus = false")
    List<InventoryAvailability> findCommonInventoryDistinctByProductName(@Param("name") String name);

    @Query(
        "select ia from InventoryAvailability ia where ia.supplier.id in (:supplierIds) and ia.activeStatus = true " +
        " and ia.hiddenStatus = false")
    List<InventoryAvailability> findAllProductsBySupplierIds(@Param("supplierIds") List<Long> supplierIds);

    List<InventoryAvailability> findAllBySupplierAndActiveStatus(User supplier, boolean activeStatus);

    @Query("select ia from InventoryAvailability ia where ia.supplier = :supplier and "
           + "(:query is null or upper(ia.productName) like CONCAT(upper(:query),'%')) and ia.activeStatus = true")
    Page<InventoryAvailability> findAllByProductNameAndSupplier(@Param("query") String query,
                                                                @Param("supplier") User supplier, Pageable pageable);

    Optional<InventoryAvailability> findByIdAndSupplier(Long id, User supplier);

    List<InventoryAvailability> findBySupplier(User supplier);

    @Query("select ia from InventoryAvailability ia where ia.supplier.id = :supplierId")
    List<InventoryAvailability> findBySupplierId(long supplierId);


    @Query("select ia from InventoryAvailability ia where ia.supplier = :supplier and "
           + " ia.productCode in (:productCodes) and ia.availability > 0 and ia.activeStatus = true and ia.hiddenStatus = false")
    List<InventoryAvailability> findProductsBySupplierAndProductCodes(@Param("supplier") User supplier,
                                                                      @Param("productCodes") List<String> productCodes);

    @Query("select ia from InventoryAvailability ia where ia.supplier.id in :supplierIds and "
           + " ia.productCode in (:productCodes) and (ia.availability > 0 or ia.supplier.settings.skipAvailabilityCheck = true) and ia.activeStatus = true and ia.hiddenStatus = false")
    List<InventoryAvailability> findProductsBySupplierIdsAndProductCodes(Set<Long> supplierIds,
                                                                         Set<String> productCodes);

    @Query("select ia from InventoryAvailability ia where ia.supplier = :supplier and "
           + " ia.productCode in (:productCodes)")
    List<InventoryAvailability> findProductsBySupplierAndProductCodesWithoutConditions(@Param("supplier") User supplier,
                                                                                       @Param("productCodes") Set<String> productCodes);

    @Query("select ia from InventoryAvailability ia where ia.supplier = :supplier and "
           + " ia.productCode in (:productCodes) and ia.activeStatus = true")
    List<InventoryAvailability> findProductsBySupplierAndProductCodesForSupplier(@Param("supplier") User supplier,
                                                                                 @Param("productCodes") List<String> productCodes);

    @Query("select ia from InventoryAvailability ia where ia.supplier = :supplier and "
           + "(:query is null or upper(ia.productName) like CONCAT(upper(:query),'%')) and ia.activeStatus = true")
    List<InventoryAvailability> findAllByProductNameAndSupplier(@Param("query") String query,
                                                                @Param("supplier") User supplier);

    @Modifying
    @Transactional
    @Query(value = "delete from InventoryAvailability ia where ia.id in (:ids)")
    void deleteByIds(Set<Long> ids);

    @Query("select ia.id from InventoryAvailability ia where ia.searchPrefixes is null")
    List<Long> findAllIdsWithSearchPrefixesNull();

    @Query("select ia from InventoryAvailability ia where ia.id in :ids")
    List<InventoryAvailability> findAllByIds(Collection<Long> ids);

    @Query("select ia from InventoryAvailability  ia where ia.id in :inventoryAvailabilityIds and ia.supplier.id in :supplierIds and ia.activeStatus = true and ia.hiddenStatus = false")
    List<InventoryAvailability> findAllByIdsAndSupplierIds(List<Long> inventoryAvailabilityIds, Set<Long> supplierIds);

    @Query("select ia from InventoryAvailability ia where ia.activeStatus = true and ia.id not in (:excludedIds) and ia.productName like concat('%', :product, '%') and ia.internalGroup is null order by ia.mrp desc")
    List<InventoryAvailability> findAllGlobally(@Param("product") String query,
                                                @Param("excludedIds") List<Long> excludedIds);


    @Query("select ia from InventoryAvailability ia where ia.supplier = :supplier "
           + "and ia.productCode in :productCodes "
           + "and ia.activeStatus = :activeStatus "
           + "and ia.hiddenStatus = false ")
    List<InventoryAvailability> findAllBySupplierAndProductCodesAndActiveStatus(User supplier, Set<String> productCodes,
                                                                                boolean activeStatus);

    @Query("select ia from InventoryAvailability ia where ia.supplier.id in (:ids) and concat(cast(ia.supplier.id as string), ':::', ia.productCode) in (:pairs)")
    List<InventoryAvailability> findAllBySupplierIDAndProductCode(@Param("pairs") List<String> search,
                                                                  @Param("ids") List<Long> supplierIds);

    @Query("select ia from InventoryAvailability ia where ia.supplier.id in (:ids) and concat(cast(ia.supplier.id as string), ':::', ia.productCode) in (:pairs)")
    List<InventoryAvailabilityView> findAllInventoryViewsBySupplierIDAndProductCode(@Param("pairs") List<String> search,
                                                                                    @Param("ids") List<Long> supplierIds);

    @Query("select ia from InventoryAvailability ia where ia.supplier = :supplier")
    List<InventoryAvailability> findAllBySupplier(User supplier);

    @Query("select ia from InventoryAvailability  ia where ia.supplier is null")
    List<InventoryAvailability> findCommonInventory();

    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("update InventoryAvailability ia set ia.activeStatus = :activeStatus, ia.availability = :quantity where ia in (:inventoryAvailabilities)")
    void updateActiveStatusAndAvailability(List<InventoryAvailability> inventoryAvailabilities,
                                           boolean activeStatus,
                                           int quantity);
}
