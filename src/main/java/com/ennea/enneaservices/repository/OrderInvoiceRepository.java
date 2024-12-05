package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.OrderInvoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface OrderInvoiceRepository extends CrudRepository<OrderInvoice, Long> {

    @Query("select orderInvoice from OrderInvoice  orderInvoice where orderInvoice.processed = :processed and orderInvoice.invoiceOrderStatus= :invoiceOrderStatus and orderInvoice.distributorId = :supplierId order by orderInvoice.id")
    List<OrderInvoice> findAllByProcessedAndInvoiceOrderStatusAndSupplier(boolean processed, String invoiceOrderStatus,
                                                                          Long supplierId);

    @Query(
        "select orderInvoice.orderNumber FROM OrderInvoice orderInvoice where orderInvoice.distributorId = :distributorId and "
        +
        "orderInvoice.orderNumber in (:orderNumbers)")
    Set<String> findExistingOrderNumbers(@Param("distributorId") long distributorId,
                                         @Param("orderNumbers") List<String> orderNumbers);

}
