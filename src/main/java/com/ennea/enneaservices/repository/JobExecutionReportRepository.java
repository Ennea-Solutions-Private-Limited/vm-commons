package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.JobExecutionReport;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

public interface JobExecutionReportRepository extends CrudRepository<JobExecutionReport, Long> {


    @Query(value = "select * from job_execution_report jer where jer.supplier_id = :supplierId and jer.type = :type " +
                   "and jer.status = :status order by jer.execution_date_time desc limit 1", nativeQuery = true)
    Optional<JobExecutionReport> findBySupplierAndTypeAndStatus(@Param("supplierId") long supplierId,
                                                                @Param("type") String type,
                                                                @Param("status") String status);

    @Modifying
    @Transactional
    @Query(value = "delete jer from job_execution_report jer " +
                   "join erp_credentials on erp_credentials.user_id = jer.supplier_id " +
                   "join erp on erp.id = erp_credentials.erp_id " +
                   "where jer.type = :type and erp.name = :erp", nativeQuery = true)
    void deleteByErpNameAndType(@Param("erp") String erp, @Param("type") String type);

    @Modifying
    @Transactional
    @Query(value = "delete jer from job_execution_report jer " +
                   "join erp_credentials on erp_credentials.user_id = jer.supplier_id " +
                   "join erp on erp.id = erp_credentials.erp_id " +
                   "where jer.type = :type and erp.name = :erp and user_id = :userId", nativeQuery = true)
    void deleteByErpNameAndTypeAndUserId(String erp, String type, Long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from JobExecutionReport jer where jer.supplier.id in (:ids)")
    void deleteBySupplierIds(Set<Long> ids);
}