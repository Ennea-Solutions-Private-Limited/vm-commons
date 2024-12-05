package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Invoice;
import com.ennea.enneaservices.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {

    @Query("select i from Invoice  i where i.invoiceNumber in (:invoiceNumbers) and i.supplier = :supplier order by i.id desc")
    List<Invoice> findByInvoiceNumbersAndSupplier(Set<String> invoiceNumbers, User supplier);

    @Query("select distinct(i.supplier.id) from Invoice i where i.customer is null")
    Set<Long> findSuppliersWhereCustomerIdIsNull();

    @Query("select i from Invoice i where (i.supplier.id = :supplierId) and (i.partyCode in (:partyCodes)) and i.customer is null")
    List<Invoice> findBySupplierIdAndPartyCodesAndCustomerNull(Long supplierId, Set<String> partyCodes);

    @Modifying
    @Transactional
    @Query(value = "delete from Invoice i where i.supplier.id = :supplierId")
    void deleteBySupplierId(long supplierId);

    @Query("select invoice from Invoice invoice where invoice.supplier = :user and invoice.paid= :paid and invoice.invoiceDate >= :fromDate order by invoice.id desc")
    List<Invoice> findAllBySupplierAndPaidStatusAndInvoiceDate(User user, boolean paid, LocalDate fromDate);

    @Query("select invoice from Invoice invoice where invoice.customer.id = :userId and invoice.updateId = :updateId")
    Optional<Invoice> findByCustomerIdAndUpdateId(Long userId, String updateId);

    @Query("select invoice from Invoice invoice where invoice.supplier = :supplier and invoice.customer = :customer and invoice.pendingAmount > 0 order by invoice.invoiceDate asc")
    List<Invoice> findAllBySupplierAndCustomerAndPending(User supplier, User customer);

    @Query("select invoice from Invoice invoice where invoice.supplier = :supplier and invoice.partyCode = :partyCode and invoice.pendingAmount > 0 order by invoice.invoiceDate asc")
    List<Invoice> findAllBySupplierAndCustomerPartyCodeAndPending(User supplier, String partyCode);

    @Query("select invoice from Invoice invoice where invoice.supplier.id = :supplierId and invoice.customer = :customer and invoice.pendingAmount > 0 and invoice.creditRequestRaised = :creditRequested order by invoice.invoiceDate asc")
    List<Invoice> findAllBySupplierIdAndCustomerAndPendingAndCreditRequested(Long supplierId, User customer,
                                                                             boolean creditRequested);

    @Query("select invoice from Invoice invoice where invoice.supplier = :supplier and invoice.customer = :user and invoice.invoiceNumber in :invoiceNumbers")
    List<Invoice> findAllByCustomerAndSupplierAndInvoiceNumbers(User user, User supplier, Set<String> invoiceNumbers);

    @Query(value = """
            SELECT '3 Months', SUM(i.net_value) AS invoiceTotal FROM `user` AS u
        LEFT JOIN invoice AS i ON i.customer_id = u.id
        AND i.invoice_date > DATE_SUB(NOW(), INTERVAL 3 MONTH)
        WHERE u.id = :userId
        GROUP BY u.id
        
        UNION
        
        SELECT '6 Months', SUM(i.net_value) AS invoiceTotal FROM `user` AS u
        LEFT JOIN invoice AS i ON i.customer_id = u.id
        AND i.invoice_date > DATE_SUB(NOW(), INTERVAL 6 MONTH)
            WHERE u.id = :userId
            GROUP BY u.id
        
                UNION
        
                SELECT '12 Months', SUM(i.net_value) AS invoiceTotal FROM `user` AS u
                LEFT JOIN invoice AS i ON i.customer_id = u.id
                AND i.invoice_date > DATE_SUB(NOW(), INTERVAL 12 MONTH)
                WHERE u.id = :userId
                GROUP BY u.id
        """, nativeQuery = true)
    List<Object[]> findAvgInvoiceValuesForUser(Long userId);

    @Modifying
    @Transactional
    @Query(value = "update Invoice i set i.paid = true, i.pendingAmount = 0 where i.supplier in :supplierList and i.invoiceDate < :date")
    void updateInvoiceToPaidByDateAndSuppliers(List<User> supplierList, LocalDate date);

    @Query("select invoice from Invoice invoice where invoice.supplier = :supplier and (invoice.invoiceNumber in :invoiceIds or invoice.pendingAmount > 0)")
    List<Invoice> findAllBySupplierInvoicesOrOutstanding(User supplier, List<String> invoiceIds);

    @Query(value = "SELECT SUM(i.net_value) FROM `invoice` i WHERE i.customer_id = :userId AND i.creation_date_time BETWEEN DATE_SUB(CURDATE(), INTERVAL :months MONTH) AND CURDATE()", nativeQuery = true)
    Double findXMonthsInvoiceValue(@Param("userId") Long userId, @Param("months") Integer months);

    @Query("select invoice from Invoice invoice where invoice.supplier = :supplier and invoice.partyCode = :partyCode and invoice.pendingAmount > 0 order by invoice.invoiceDate asc")
    List<Invoice> findAllBySupplierAndPartyCodeAndPendingAmount(User supplier, String partyCode);
}
