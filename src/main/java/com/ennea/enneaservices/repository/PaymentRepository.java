package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Payment;
import com.ennea.enneaservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    @Query("select payment from Payment payment where payment.customer = :customer"
           + " and (:statusId = 0 or payment.collectionMetadata.status.id = :statusId)"
           + " and (:query is null or lower(payment.supplier.businessName) like CONCAT('%',lower(:query),'%'))"
           + "and payment.creationDateTime >= :startDate and payment.creationDateTime <= :endDate"
           + " order by payment.modificationDateTime desc")
    Page<Payment> findByCustomerAndStatusIdAndSupplierBusinessName(User customer, int statusId, String query,
                                                                   LocalDateTime startDate, LocalDateTime endDate,
                                                                   Pageable pageable);

    @Query("select payment from Payment payment "
           + "where payment.supplier = :supplier and (:statusId = 0 or payment.collectionMetadata.status.id = :statusId)"
           + "and (:query is null or lower(payment.customerDetails.businessName) like CONCAT('%',lower(:query),'%'))"
           + "and payment.creationDateTime >= :startDate and payment.creationDateTime <= :endDate"
           + " order by payment.modificationDateTime desc")
    Page<Payment> findBySupplierAndStatusIdAndCustomerPartyName(User supplier, int statusId, String query,
                                                                LocalDateTime startDate, LocalDateTime endDate,
                                                                Pageable pageable);


    @Query("select payment.id from Payment payment "
           + " where payment.supplier = :supplier and payment.customerDetails.partyCode  in :partyCodes and (:statusId = 0 or payment.collectionMetadata.status.id = :statusId )"
           + " and (:query is null or lower(payment.customerDetails.businessName) like CONCAT('%',lower(:query),'%'))"
           + "and payment.creationDateTime >= :startDate and payment.creationDateTime <= :endDate"
           + " order by payment.modificationDateTime desc")
    List<Long> findPaymentIdsBySupplierAndPartyCodesAndStatusIdAndCustomerPartyName(User supplier,
                                                                                    Set<String> partyCodes,
                                                                                    int statusId,
                                                                                    LocalDateTime startDate,
                                                                                    LocalDateTime endDate,
                                                                                    String query);

    @Query("select payment from Payment payment where payment.collectionMetadata.referenceId = :referenceId")
    Optional<Payment> findByCollectionReferenceId(String referenceId);

    @Query("select payment from Payment payment where payment.supplier = :supplier" +
           " and payment.collectionMetadata.status.name in (:collectionStatuses)" +
           " and payment.disbursalMetadata.status.name = :disbursalStatus")
    List<Payment> findBySupplierAndCollectionStatusAndDisbursalStatus(User supplier, List<String> collectionStatuses,
                                                                      String disbursalStatus);

    @Query("select payment from Payment payment where payment.id in (:ids) and payment.supplier = :supplier" +
           " and payment.collectionMetadata.status.name in (:collectionStatuses)" +
           " and payment.disbursalMetadata.status.name = :disbursalStatus")
    List<Payment> findBySupplierAndIdsAndCollectionStatusAndDisbursalStatus(List<Long> ids, User supplier,
                                                                            List<String> collectionStatuses,
                                                                            String disbursalStatus);

    @Query("select payment from Payment payment where payment.supplier = :supplier" +
           " and payment.disbursalMetadata.gateway.name = :disbursalGateway" +
           " and payment.disbursalMetadata.status.name = :disbursalStatus")
    List<Payment> findBySupplierAndDisbursalStatus(User supplier, String disbursalGateway, String disbursalStatus);

    @Query("select payment from Payment payment where payment.supplier = :supplier" +
           " and payment.disbursalMetadata.status.name = :disbursalStatus and payment.paymentType.type = :paymentType and payment.exported = :exported")
    List<Payment> findBySupplierAndDisbursalStatusAndPaymentTypeAndExported(User supplier, String disbursalStatus,
                                                                            String paymentType, boolean exported);

    @Query("select payment from Payment payment where payment.id in :paymentIds order by payment.id desc")
    Page<Payment> findByPaymentIds(List<Long> paymentIds, Pageable pageable);

    @Query("select payment from Payment payment where payment.supplier = :supplier" +
           " and payment.modificationDateTime >= :startDateTime and  payment.modificationDateTime <= :endDateTime order by payment.id desc")
    List<Payment> findPaymentsBySupplierAndModificationDateTimeWithinRange(User supplier, LocalDateTime startDateTime,
                                                                           LocalDateTime endDateTime);

    @Query("select payment from Payment payment where payment.customer = :customer" +
           " and payment.modificationDateTime >= :startDateTime and  payment.modificationDateTime <= :endDateTime order by payment.id desc")
    List<Payment> findPaymentsByCustomerAndModificationDateTimeWithinRange(User customer, LocalDateTime startDateTime,
                                                                           LocalDateTime endDateTime);

    @Query("select p from Payment p where p.supplier = :supplier and p.paymentType.type = :paymentType and p.id in :paymentIds ")
    List<Payment> findAllPaymentsBySupplierAndPaymentIdsAndPaymentType(User supplier, String paymentType,
                                                                       List<Long> paymentIds);

    @Query("select p from Payment p where p.collectionMetadata.status.name = :collectionStatusPending and (FUNCTION('DATEDIFF', p.creationDateTime , CURRENT_DATE) = :daysDiff)")
    List<Payment> findByCollectionStatusAndDiff(String collectionStatusPending, int daysDiff);

    @Query("SELECT p FROM Payment p JOIN p.collectionMetadata cm LEFT JOIN p.disbursalMetadata dm on p.disbursalMetadata.id = dm.id WHERE p.paymentType.id = 3 AND (cm.status.id = 5 OR (p.disbursalMetadata IS NOT NULL AND dm.status.id = 1))")
    List<Payment> findAllPaymentsByPaymentTypeAndCollectionAndDisbursalStatus(String paymentTypeCredit,
                                                                              String paymentCollectionStatus,
                                                                              String paymentDisbursalStatus);

    @Query("SELECT p FROM Payment p WHERE p.paymentType.type IN :paymentTypes AND p.collectionMetadata.status.name IN :collectionStatuses AND p.creationDateTime BETWEEN :prevDate AND CURRENT_TIME")
    List<Payment> findByCollectionStatusesAndDaysDiff(List<String> paymentTypes, List<String> collectionStatuses,
                                                      LocalDateTime prevDate);

    @Transactional
    @Modifying
    @Query(value = "update Payment p set p.exported = true where p.id in :paymentIds")
    void updateExported(List<Long> paymentIds);
}
