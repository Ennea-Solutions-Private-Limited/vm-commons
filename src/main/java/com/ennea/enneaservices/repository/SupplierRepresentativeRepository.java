package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Dto.UserMetadataView;
import com.ennea.enneaservices.model.RequestStatus;
import com.ennea.enneaservices.model.SupplierRepresentative;
import com.ennea.enneaservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SupplierRepresentativeRepository extends CrudRepository<SupplierRepresentative, Long> {

    List<SupplierRepresentative> findByRepresentative(User representative);

    @Query("select sr from SupplierRepresentative sr where sr.supplier = :supplier and "
           + " (:requestStatusId = 0 or sr.requestStatus.id = :requestStatusId) and "
           + " (:query is null or lower(sr.representative.contactPerson) like CONCAT('%',lower(:query),'%')) order by sr.id desc")
    Page<SupplierRepresentative> findAllBySupplierAndStatusAndRepresentativeContactPerson(
        @Param("supplier") User supplier,
        @Param("requestStatusId") int requestStatusId, @Param("query") String query, Pageable pageable);

    @Query("select sr from SupplierRepresentative sr where sr.supplier = :supplier and (:requestStatusId = 0 or sr.requestStatus.id = :requestStatusId)")
    List<SupplierRepresentative> findAllBySupplierAndStatus(@Param("supplier") User supplier,
                                                            @Param("requestStatusId") int requestStatusId);

    @Query("select sr from SupplierRepresentative sr where sr.id = :id and sr.supplier = :supplier")
    Optional<SupplierRepresentative> findByIdAndSupplier(@Param("id") long id, @Param("supplier") User supplier);

    @Query(
        "select sr from SupplierRepresentative sr where sr.representative.id = :representativeId and sr.supplier = :supplier "
        + "and sr.requestStatus.status = :status")
    Optional<SupplierRepresentative> findByRepresentativeIdAndSupplierAndRequestStatus(
        @Param("representativeId") long representativeId,
        @Param("supplier") User supplier, @Param("status") String status);

    @Query(
        "select sr from SupplierRepresentative sr where sr.representative.id = :representativeId and sr.supplier.id = :supplierId "
        + "and sr.requestStatus.status = :status")
    Optional<SupplierRepresentative> findByRepresentativeIdAndSupplierIdAndRequestStatus(long representativeId,
                                                                                         long supplierId,
                                                                                         String status);

    @Query("select sr.representative.id as id, " +
           "sr.representative.businessName as businessName, " +
           "sr.representative.contactPerson as contactPerson from SupplierRepresentative sr " +
           "where sr.supplier = :supplier and sr.requestStatus = :requestStatus")
    List<UserMetadataView> findAllBySupplierAndRequestStatus(User supplier, RequestStatus requestStatus);


    @Query("select  sr from SupplierRepresentative sr where sr.supplier = :supplier and sr.requestStatus.status =:status")
    List<SupplierRepresentative> findAllBySupplierAndRequestStatus(User supplier, String status);

    List<SupplierRepresentative> findByRepresentativeAndRequestStatus(User representative, RequestStatus requestStatus);

    @Query("select sr from SupplierRepresentative sr where sr.representative = :representative and sr.requestStatus.status = :requestStatus")
    List<SupplierRepresentative> findByRepresentativeAndRequestStatus(User representative, String requestStatus);

    @Query("select sr.supplier.id from SupplierRepresentative sr where sr.requestStatus.id = :requestStatusId and sr.representative.id = :representativeId")
    List<Long> findByRepresentativeAndRequestStatus(long representativeId, int requestStatusId);

    @Query("select sr from SupplierRepresentative sr where sr.representative.id = :representativeId and sr.supplier.id in (:supplierIds)")
    List<SupplierRepresentative> findByRepresentativeAndSupplierIds(Long representativeId, List<Long> supplierIds);

    @Query("select sr from SupplierRepresentative sr where sr.representative = :user and (:statusId = 0 or sr.requestStatus.id = :statusId)")
    List<SupplierRepresentative> findAllByRepresentativeAndStatus(User user, int statusId);

    @Query("select sr from SupplierRepresentative sr where sr.supplier = :supplier and sr.representative.id = :representativeId")
    Optional<SupplierRepresentative> findBySupplierAndRepresentativeId(User supplier, Long representativeId);

    @Query("select sr from SupplierRepresentative sr where sr.supplier = :supplier and sr.representative = :representative and sr.requestStatus.status = :status")
    Optional<SupplierRepresentative> findBySupplierAndRepresentativeAndRequestStatus(User supplier,
                                                                                     User representative,
                                                                                     String status);

    @Query("select sr from SupplierRepresentative sr where sr.supplier.id = :supplierId and sr.representative.id = :representativeId and sr.requestStatus.status = :requestStatus")
    Optional<SupplierRepresentative> findByRepresentativeIdAndSupplierIdAndStatus(long representativeId,
                                                                                  long supplierId,
                                                                                  String requestStatus);
}
