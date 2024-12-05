package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.InvoiceStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceStatusRepository extends CrudRepository<InvoiceStatus, Integer> {

    @Query("select invoiceStatus from InvoiceStatus invoiceStatus where invoiceStatus.status = :status")
    InvoiceStatus findByStatus(String status);

    List<InvoiceStatus> findAllByOrderById();
}
