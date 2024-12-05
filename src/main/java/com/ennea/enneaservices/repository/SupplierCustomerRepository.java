package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Dto.CustomerPartyNameView;
import com.ennea.enneaservices.model.RequestStatus;
import com.ennea.enneaservices.model.SupplierCustomer;
import com.ennea.enneaservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SupplierCustomerRepository extends CrudRepository<SupplierCustomer, Long> {

    @Query(
        "select sc from SupplierCustomer sc where sc.supplier = :supplier and (:statusId = 0 or sc.requestStatus.id = :statusId) and "
        + "(:nonAssociated = false or sc.customerPartyCode is null ) and "
        + "(:query is null or lower(sc.customerPartyName) like CONCAT('%',lower(:query),'%') or lower(concat(sc.customer.phoneNumber,'')) like CONCAT('%',lower(:query),'%') )")
    Page<SupplierCustomer> findSupplierCustomerBySupplierAndCustomerPartyNameAndNonAssociatedPartyCode(
        @Param("supplier") User supplier, @Param("statusId") int statusId,
        @Param("query") String query, Pageable pageable, @Param("nonAssociated") boolean nonAssociated);


    @Query(
        "select sc from SupplierCustomer sc where sc.customer = :customer and (:statusId = 0 or sc.requestStatus.id = :statusId) and "
        + "(:query is null or lower(sc.supplier.businessName) like CONCAT('%',lower(:query),'%'))")
    Page<SupplierCustomer> findSupplierCustomerByCustomerAndSupplierBusinessName(@Param("customer") User customer,
                                                                                 @Param("statusId") int statusId,
                                                                                 @Param("query") String query,
                                                                                 Pageable pageable);

    @Query(
        "select sc from SupplierCustomer sc where sc.customer = :customer and sc.requestStatus.status = :requestStatus and "
        + "(:query is null or lower(sc.supplier.businessName) like CONCAT('%',lower(:query),'%'))")
    Page<SupplierCustomer> findSupplierCustomerByCustomerAndRequestStatusAndSupplierBusinessName(User customer,
                                                                                                 String requestStatus,
                                                                                                 String query,
                                                                                                 Pageable pageable);

    @Query("select sc from SupplierCustomer sc where sc.supplier = :supplier and sc.customerPartyCode in (:partyCodes)")
    List<SupplierCustomer> findSupplierCustomerBySupplierAndCustomerPartyCodes(User supplier, Set<String> partyCodes);

    List<SupplierCustomer> findSupplierCustomerBySupplier(User supplier);

    @Query("select sc from SupplierCustomer sc where sc.customer in (:users) and sc.requestStatus.status = :status")
    List<SupplierCustomer> findAllApprovedSupplierCustomerByCustomer(List<User> users, String status);

    SupplierCustomer findSupplierCustomerBySupplierAndCustomer(User supplier, User customer);

    SupplierCustomer findFirstBySupplierIdAndCustomerId(Long supplier, Long customer);

    @Query("select sc.supplier.id as supplierId, sc.customer.id as customerId, sc.customerPartyName as customerPartyName, sc.customerPartyCode as customerPartyCode from SupplierCustomer  sc where (sc.supplier.id in :supplierIds and sc.customer.id in :customerIds)")
    List<CustomerPartyNameView> findCustomerPartyNameViewBySupplierAndCustomerPairs(Set<Long> supplierIds,
                                                                                    Set<Long> customerIds);

    SupplierCustomer findSupplierCustomerBySupplierAndCustomerAndRequestStatus(User supplier, User customer,
                                                                               RequestStatus requestStatus);

    @Query(
        "select sc from SupplierCustomer sc where sc.supplier = :supplier and sc.requestStatus.status = :requestStatus"
        + " and (:query is null or lower(sc.customerPartyName) like CONCAT('%',lower(:query),'%'))")
    List<SupplierCustomer> findSupplierCustomerBySupplierAndRequestStatusAndCustomerPartyName(
        @Param("supplier") User supplier,
        @Param("requestStatus") String requestStatus, @Param("query") String query);

    List<SupplierCustomer> findSupplierCustomerByCustomerAndRequestStatus(User customer, RequestStatus requestStatus);

    @EntityGraph(attributePaths = {"supplier.settings", "customer", "requestStatus"})
    @Query("select sc from SupplierCustomer sc where sc.customer.id = :customer and sc.requestStatus.id = :requestStatus")
    List<SupplierCustomer> findSupplierCustomerByCustomerAndRequestStatusWithSupplierSettings(long customer,
                                                                                              int requestStatus);

    @Query(
        "select distinct(sc) from SupplierCustomer sc where sc.customer = :customer and sc.supplier.userStatus.status = :userStatus "
        + "and sc.requestStatus.status = :requestStatus and (:query is null or lower(sc.supplier.businessName) like CONCAT('%',lower(:query),'%')) "
        + "and sc.supplier.roleConfiguration.role.name in (:userRoles)")
    List<SupplierCustomer> findSupplierCustomerByCustomerAndRequestStatusAndSupplierBusinessName(
        @Param("customer") User customer,
        @Param("userStatus") String userStatus, @Param("requestStatus") String requestStatus,
        @Param("query") String query,
        @Param("userRoles") List<String> userRoles);

    List<SupplierCustomer> findAll();

    @Query("select sc from SupplierCustomer sc where sc.supplier.id = :supplierId and sc.customerPartyCode is not null and (FUNCTION('DATEDIFF', CURRENT_DATE, sc.modificationDateTime) < 2)")
    List<SupplierCustomer> getRecentModifiedSupplierCustomerBySupplierId(Long supplierId);

    List<SupplierCustomer> findSupplierCustomerListBySupplierAndRequestStatus(User supplier,
                                                                              RequestStatus requestStatus);

    @Query("select sc from SupplierCustomer sc where sc.supplier.id = :supplierId and sc.customer.id = :customerId")
    Optional<SupplierCustomer> findSupplierCustomerBySupplierIdAndCustomerId(@Param("supplierId") long supplierId,
                                                                             @Param("customerId") long customerId);

    @Query("select sc from SupplierCustomer sc where sc.supplier = :supplier and sc.customer.roleConfiguration.role.name in (:roles)")
    List<SupplierCustomer> findSupplierCustomerBySupplierAndCustomerRoles(@Param("supplier") User supplier,
                                                                          @Param("roles") List<String> roles);

    @Query("select sc from SupplierCustomer sc where sc.id = :id and sc.supplier = :supplier")
    Optional<SupplierCustomer> findByIdAndSupplier(@Param("id") long id, @Param("supplier") User supplier);

    @Query("select sc from SupplierCustomer sc where sc.supplier = :supplier and sc.customerPartyCode = :customerPartyCode")
    Optional<SupplierCustomer> findBySupplierAndCustomerPartyCode(@Param("supplier") User supplier,
                                                                  @Param("customerPartyCode") String customerPartyCode);

    @Query("select sc from SupplierCustomer sc where sc.supplier = :supplier and sc.customer.id in :customerIds")
    List<SupplierCustomer> findBySupplierAndCustomerIds(User supplier, Set<Long> customerIds);

    @Query("select sc.customer.id from SupplierCustomer sc where sc.supplier = :supplier and sc.requestStatus.status = :requestStatus")
    Set<Long> findCustomerIdsBySupplierAndRequestStatus(User supplier, String requestStatus);

    @Query("select sc from SupplierCustomer sc where sc.customer = :user and sc.requestStatus.status = :status and sc.supplier.id in :supplierIds")
    List<SupplierCustomer> findSupplierCustomersByCustomerAndRequestStatusAndSupplierIds(User user, String status,
                                                                                         Set<Long> supplierIds);

    @Transactional
    @Modifying
    @Query(value = "update SupplierCustomer sc set sc.requestStatus = :updatedRequestStatus, sc.modificationDateTime = :modificationTime where sc.requestStatus = :previousRequestStatus and sc.supplier = :user")
    void updateRequestStatusAndModificationDateTimeBySupplier(User user, RequestStatus previousRequestStatus,
                                                              RequestStatus updatedRequestStatus,
                                                              LocalDateTime modificationTime);

    @Query(value = "select sc from SupplierCustomer sc where sc.customer = :user and sc.supplier.id in :supplierIds")
    List<SupplierCustomer> findSupplierCustomersByCustomerAndSupplierIds(User user, Set<Long> supplierIds);

    @Query(value = "select sc from SupplierCustomer sc where sc.customer = :user and sc.supplier.id = :supplierId and sc.requestStatus.status = :requestStatus")
    Optional<SupplierCustomer> findSupplierCustomersByCustomerAndSupplierIdAndRequestStatus(User user, long supplierId,
                                                                                            String requestStatus);

    @Query(value = "select distinct sc.supplier.id from SupplierCustomer sc where sc.customer = :user")
    Set<Long> findSupplierCustomerSupplierIdsByCustomer(User user);

    @Query("select sc from SupplierCustomer sc where sc.customer = :customer and sc.supplier.roleConfiguration.role.name in (:roles)")
    List<SupplierCustomer> findSupplierCustomerByCustomerAndSupplierRoles(@Param("customer") User customer,
                                                                          @Param("roles") List<String> roles);

    @Query("select sc from SupplierCustomer sc where sc.supplier = :supplier and sc.requestStatus.status = :status and sc.customerPartyCode in :partyCodes")
    List<SupplierCustomer> findSupplierCustomerBySupplierAndRequestStatusAndPartyCodes(User supplier, String status,
                                                                                       Set<String> partyCodes);

    @Query("select sc from SupplierCustomer sc where sc.supplier.id = :supplierId and sc.customerPartyCode = :partyCode and sc.requestStatus.status = :requestStatus")
    Optional<SupplierCustomer> findBySupplierAndCustomerPartyCodeAndRequestStatus(long supplierId, String partyCode,
                                                                                  String requestStatus);

    @Query("select sc from SupplierCustomer sc where sc.customer = :customer and sc.requestStatus.status = :requestStatus")
    List<SupplierCustomer> findSupplierCustomersByCustomerAndRequestStatus(User customer, String requestStatus);

    @Query("select sc from SupplierCustomer sc where sc.customer = :customer and sc.requestStatus.status = :requestStatus")
    List<SupplierCustomer> findSupplierCustomerByCustomerAndRequestStatus(User customer, String requestStatus);

    @Query(value = "select sc from SupplierCustomer sc where sc.customer = :customer")
    List<SupplierCustomer> findSupplierCustomerByCustomer(User customer);

}
