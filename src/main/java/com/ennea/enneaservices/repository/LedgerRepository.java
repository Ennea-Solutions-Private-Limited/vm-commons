package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Ledger;
import com.ennea.enneaservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LedgerRepository extends CrudRepository<Ledger, Long> {

    @Query("select ledger from Ledger ledger where ledger.supplier = :supplier")
    List<Ledger> findAllBySupplier(@Param("supplier") User supplier);

    @Query("select ledger from Ledger ledger where ledger.supplier = :supplier and ledger.alias = :customerAlias")
    Optional<Ledger> findBySupplierAndAlias(User supplier, String customerAlias);

    @Query("select ledger.id from Ledger ledger where ledger.supplier.id = :supplierId")
    List<Long> findAllIdsBySupplier(long supplierId);

    @Query("select ledger from Ledger ledger where ledger.supplier = :supplier and (:query is null "
           + "or lower(ledger.partyName) like CONCAT('%', lower(:query),'%') "
           + "or lower(ledger.alias) like CONCAT('%', lower(:query),'%') "
           + "or lower(ledger.address) like CONCAT('%', lower(:query),'%') "
           + "or lower(ledger.drugLicense) like CONCAT('%', lower(:query),'%') "
           + "or lower(ledger.phoneNumber) like CONCAT('%', lower(:query),'%'))")
    Page<Ledger> findBySupplierAndPartyNameAndAddressAndDrugLicenseAndPhoneNumber(User supplier, String query,
                                                                                  Pageable pageable);

    @Query("select ledger from Ledger ledger where ledger.id in :ids and ledger.active = :status and (:query is null "
           + "or lower(ledger.partyName) like CONCAT('%', lower(:query),'%') "
           + "or lower(ledger.alias) like CONCAT('%', lower(:query),'%') "
           + "or lower(ledger.address) like CONCAT('%', lower(:query),'%') "
           + "or lower(ledger.drugLicense) like CONCAT('%', lower(:query),'%') "
           + "or lower(ledger.phoneNumber) like CONCAT('%', lower(:query),'%'))")
    Page<Ledger> findByIDsAndPartyNameAndAddressAndDrugLicenseAndPhoneNumberAndStatus(Set<Long> ids, String query,
                                                                                      boolean status,
                                                                                      Pageable pageable);

    @Query("select ledger from Ledger ledger where ledger.supplier = :supplier and (:query is null "
           + "or lower(ledger.partyName) like CONCAT('%', lower(:query),'%') "
           + "or lower(ledger.alias) like CONCAT('%', lower(:query),'%') "
           + "or lower(ledger.address) like CONCAT('%', lower(:query),'%'))")
    List<Ledger> findBySupplierAndPartyNameAndAddressAndAlias(User supplier, String query);

    @Query(
        "select ledger from Ledger ledger where ledger.supplier = :supplier and ledger.salesmanCode in :salesmanCodes and (:query is null "
        + "or lower(ledger.partyName) like CONCAT('%', lower(:query),'%') "
        + "or lower(ledger.alias) like CONCAT('%', lower(:query),'%') "
        + "or lower(ledger.address) like CONCAT('%', lower(:query),'%'))")
    List<Ledger> findBySupplierAndPartyNameAndSalesmanCodeAndAddressAndAlias(User supplier, Set<String> salesmanCodes,
                                                                             String query);

    @Query("select ledger from Ledger ledger where ledger.supplier = :supplier and ledger.partyCode in (:partyCodes)")
    List<Ledger> findAllBySupplierAndPartyCodes(User supplier, Set<String> partyCodes);

    @Modifying
    @Transactional
    @Query(value = "delete from Ledger l where l.supplier.id = :supplierId")
    void deleteBySupplierId(long supplierId);

    @Query("select ledger from Ledger ledger where ledger.supplier = :supplier and ledger.partyCode = :partyCode")
    Optional<Ledger> findBySupplierAndPartyCode(User supplier, String partyCode);

    @Query(
        "select ledger from Ledger ledger where ledger.supplier = :supplier and ledger.partyCode = :partyCode and ledger.active = "
        +
        ":status")
    Optional<Ledger> findBySupplierAndPartyCodeAndActiveStatus(User supplier, String partyCode, boolean status);

    @Query("select ledger from Ledger ledger where ledger.supplier = :supplier and ledger.salesmanCode in :salesmanCodes")
    List<Ledger> findAllBySupplierAndSalesmanCodes(User supplier, Set<String> salesmanCodes);

    @Query("select ledger from Ledger ledger where ledger.supplier = :supplier and ledger.active = :activeStatus")
    List<Ledger> findAllBySupplierAndActiveStatus(User supplier, boolean activeStatus);

    @Query("select ledger from Ledger ledger where ledger.supplier in :supplierList and ledger.partyCode = :partyCode")
    List<Ledger> findAllBySuppliersAndPartyCode(List<User> supplierList, String partyCode);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ledger l WHERE l.supplier = :supplier")
    int deleteBySupplier(User supplier);

}

