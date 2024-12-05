package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Offer;
import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.model.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    @Query("select offer from Offer offer where offer.supplier = :supplier and (:query is null or "
           + "(lower(offer.userGroup.name) like concat('%',lower(:query),'%'))) order by offer.id desc ")
    Page<Offer> findAllBySupplierAndUserGroupName(@Param("supplier") User supplier, @Param("query") String query,
                                                  Pageable pageable);

    @Query(
        "select offer from Offer offer, in(offer.userGroup.members) as members ,SupplierCustomer as sc  where :customer in (members.customer) "
        +
        "and sc.customer = :customer and sc.supplier = offer.supplier and sc.requestStatus.status = :requestStatus and offer.activeStatus = true and "
        + "(:query is null or (lower(offer.supplier.businessName) like concat('%',lower(:query),'%')))  order by offer.id desc ")
    Page<Offer> findAllByCustomerAndSupplierBusinessName(@Param("customer") User customer, @Param("query") String query,
                                                         @Param("requestStatus") String requestStatus,
                                                         Pageable pageable);

    @Query("select offer from Offer offer where offer.customer = :customer and offer.supplier = :supplier")
    List<Offer> findByCustomerAndSupplier(User customer, User supplier);

    Optional<Offer> findByIdAndSupplier(long id, User supplier);

    List<Offer> findBySupplierAndUserGroup(User supplier, UserGroup userGroup);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update offer off left join inventory_availability ia on off.inventory_availability_id = ia.id set off.active_status = 0 where off.end_date  < :endDate or (( off.inventory_availability_id is not null ) AND ( ia.availability = 0 OR ia.active_status = 0))")
    void deactivateCoupons(@Param("endDate") LocalDate endDate);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update offer offer left join inventory_availability ia on offer.inventory_availability_id = ia.id set offer.active_status = 1 where offer.active_status = 0 and offer.start_date= :startDate and (offer.inventory_availability_id is null or (ia.availability > 0 and ia.active_status = 1))")
    void activateCoupons(LocalDate startDate);

    @Query("select offer from Offer offer where offer.notificationStatus = :notificationStatus")
    List<Offer> findAllByNotificationStatus(boolean notificationStatus);

    List<Offer> findAllBySupplierAndInventoryAvailabilityNotNull(User supplier);
}
