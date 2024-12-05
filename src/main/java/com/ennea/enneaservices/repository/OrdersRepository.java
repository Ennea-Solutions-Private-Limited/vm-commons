package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Dto.OrderDateView;
import com.ennea.enneaservices.model.Order;
import com.ennea.enneaservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrdersRepository extends CrudRepository<Order, Long> {

    @Query("select o from Order o where o.id in (:orderIds)")
    List<Order> findAllByIds(@Param("orderIds") Set<Long> orderIds);

    @Query(
        "select o.id as id,o.orderStatus as orderStatus,o.orderValue as orderValue , o.customerDetails.businessName as businessName from Order o where o.customer = :customer "
        + "and o.creationDateTime >= :startDate and  o.creationDateTime <= :endDate")
    List<AnalyticsOrder> findAllCustomerOrdersBetweenStartDateAndEndDate(@Param("customer") User customer,
                                                                         @Param("startDate") LocalDateTime startDate,
                                                                         @Param("endDate") LocalDateTime endDate);

    @Query(
        "select o.id as id,o.orderStatus as orderStatus,o.orderValue as orderValue , o.customerDetails.businessName as businessName from Order o where o.supplier = :supplier "
        + "and o.creationDateTime >= :startDate and  o.creationDateTime <= :endDate")
    List<AnalyticsOrder> findAllSupplierOrdersBetweenStartDateAndEndDate(@Param("supplier") User supplier,
                                                                         @Param("startDate") LocalDateTime startDate,
                                                                         @Param("endDate") LocalDateTime endDate);

    @Query(
        "select o.id as id,o.orderStatus as orderStatus,o.orderValue as orderValue, o.customerDetails.businessName as businessName from Order o where o.supplier = :supplier "
        + "and o.creationDateTime >= :startDate and  o.creationDateTime <= :endDate and o.customerDetails.partyCode in :customerPartyCodes")
    List<AnalyticsOrder> findAllSupplierOrdersBetweenStartDateAndEndDateAndCustomerPartyCodes(User supplier,
                                                                                              LocalDateTime startDate,
                                                                                              LocalDateTime endDate,
                                                                                              Set<String> customerPartyCodes);

    @Query("select o from Order o where o.supplier = :supplier and o.orderStatus.status in (:orderStatus)")
    List<Order> findAllSupplierOrdersByStatuses(@Param("supplier") User supplier,
                                                @Param("orderStatus") List<String> orderStatus);

    @Query(
        "select o  from Order o where o.orderStatus.id in :orderStatusIds and o.customer = :customer and " +
        "(:query is null or lower(o.supplier.businessName) like CONCAT('%',lower(:query),'%')) and " +
        "o.creationDateTime >= :startDate and  o.creationDateTime <= :endDate order by o.id desc ")
    Page<Order> findAllOrdersBetweenStartDateAndEndDateByCustomerAndOrderStatusAndSupplierBusinessNameOrderByIdDesc(
        @Param("customer") User customer,
        @Param("orderStatusIds") List<Integer> orderStatusIds,
        @Param("query") String query,
        @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    @Query(
        "select o  from Order o where o.orderStatus.id in :orderStatusIds and o.supplier = :supplier and " +
        "(:query is null or lower(o.customerDetails.businessName) like CONCAT('%',lower(:query),'%')) and  " +
        "o.creationDateTime >= :startDate and  o.creationDateTime <= :endDate order by o.id desc ")
    Page<Order> findAllOrdersBetweenStartDateAndEndDateBySupplierAndOrderStatusAndCustomerBusinessNameOrderByIdDesc(
        @Param("supplier") User supplier,
        @Param("orderStatusIds") List<Integer> orderStatusIds,
        @Param("query") String query,
        @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    @Modifying
    @Transactional
    @Query(
        "update Order o set o.orderStatus.id = :inTransitStatusId, o.modificationDateTime = :modificationDateTime where o.supplier.id = :supplierId"
        + " and o.id in (:orderIds) and o.orderStatus.id = :invoicingStatusId")
    void updateOrderStatusByOrderIds(long supplierId, List<Long> orderIds, int inTransitStatusId, int invoicingStatusId,
                                     LocalDateTime modificationDateTime);

    @Modifying
    @Transactional
    @Query("update Order o set o.orderStatus.id = :inTransitStatusId where o.supplier.id = :supplierId and o.orderStatus.id in :toBeMovedStatuses")
    void updateOrderStatusBySupplierAndStatusIds(long supplierId, List<Integer> toBeMovedStatuses,
                                                 int inTransitStatusId);

    @Query(
        "select distinct (o.customer) from Order o where o.supplier = :supplier and o.orderStatus.status = :status ")
    List<User> findCustomersWithOrderStatus(@Param("supplier") User supplierId,
                                            @Param("status") String status);

    @Modifying
    @Transactional
    @Query("update Order o set o.orderStatus.id = :finalOrderStatus where o.orderStatus.id = :initialOrderStatus"
           + " and o.supplier in (:suppliers) and (FUNCTION('DATEDIFF', CURRENT_DATE, o.modificationDateTime) > :duration)")
    void updateOrderStatusByDuration(@Param("initialOrderStatus") int initialOrderStatus,
                                     @Param("finalOrderStatus") int finalOrderStatus,
                                     @Param("suppliers") List<User> suppliers, @Param("duration") int duration);

    @Query("select o from Order o where o.supplier.id = :id and o.orderStatus.status = :orderReceived")
    List<Order> findAllOrdersBySupplierAndOrderStatus(Long id, String orderReceived);

    @Modifying
    @Transactional
    @Query("update Order o set o.orderStatus.id = :invoicingOrderStatusId where o.supplier.id = :supplierId"
           + " and o.id in (:orderIds) and o.orderStatus.id = :receivedOrderStatusId")
    void updateOrderStatusBySupplierAndOrderIdsAndOldStatus(@Param("supplierId") long supplierId,
                                                            @Param("orderIds") List<Long> orderIds,
                                                            @Param("invoicingOrderStatusId") int invoicingOrderStatusId,
                                                            @Param("receivedOrderStatusId") int receivedOrderStatusId);


    @Query("select o from Order o where o.rewardProcessed = false")
    List<Order> findAllOrdersWithRewardUnprocessedStatus();

    @Query(nativeQuery = true, value = "SELECT * FROM ennea.order o WHERE o.customer_id = :customerId AND o.supplier_id IN (:supplierIds) ORDER BY o.id DESC LIMIT 5")
    List<Order> findFirst5ByCustomerOrderByIdDesc(Long customerId, List<Long> supplierIds);

    @Query("select o from Order o where o.id in :orderIds order by o.id desc")
    Page<Order> findAllByIdsAndOrderByIdAndByPageable(List<Long> orderIds, Pageable pageable);

    @Query("select o from Order o where o.supplier.id = :supplierId and o.orderStatus.status = :orderStatus and o.referenceId in (:orderNumbers)")
    List<Order> findBySupplierAndOrderStatusAndOrderNumbers(long supplierId, String orderStatus,
                                                            List<String> orderNumbers);

    @Query("select o from Order o where o.supplier = :supplier and o.id = :id")
    Optional<Order> findBySupplierAndId(User supplier, long id);

    @Query(
        "select o from Order o where o.supplier = :supplier and o.customer is null and o.customerDetails.partyCode = :customerPartyCode "
        + "and o.orderStatus.status = :status")
    List<Order> findBySupplierAndCustomerNullAndPartyCodeAndStatus(User supplier, String customerPartyCode,
                                                                   String status);

    @Query("select o from Order o where o.supplier = :supplier and o.customer = :customer and o.customerDetails.partyCode is null")
    List<Order> findAllBySupplierAndCustomerAndPartyCodeNull(User supplier, User customer);

    @Query("select o from Order o where o.supplier = :supplier and o.id in :orderIds")
    List<Order> findBySupplierAndIds(User supplier, List<Long> orderIds);

    @Query("select o from Order o where o.supplier = :supplier "
           + "and o.customerDetails.partyCode in :partyCodes "
           + "and o.orderStatus.id in :statusIds "
           + "and o.creationDateTime >= :startDate "
           + "and o.creationDateTime <= :endDate "
           + "and (:query is null or lower(o.customerDetails.businessName) like CONCAT('%',lower(:query),'%')) ")
    List<Order> findAllOrdersBySupplierAndStatusAndPartyCodesAndTimeRangeAndCustomerPartyName(
        User supplier,
        Set<String> partyCodes,
        List<Integer> statusIds,
        String query,
        LocalDateTime startDate,
        LocalDateTime endDate);

    @Query("select o from Order o where o.supplier = :supplier "
           + "and o.orderStatus.id in :statusIds "
           + "and o.creationDateTime >= :startDate "
           + "and o.creationDateTime <= :endDate "
           + "and (:query is null or lower(o.customerDetails.businessName) like CONCAT('%',lower(:query),'%')) ")
    List<Order> findAllOrdersBySupplierAndStatusAndTimeRangeAndCustomerPartyName(User supplier,
                                                                                 List<Integer> statusIds,
                                                                                 String query,
                                                                                 LocalDateTime startDate,
                                                                                 LocalDateTime endDate);

    @Query(
        "SELECT o FROM Order o JOIN o.details od WHERE o.customer = :customer AND  o.orderStatus.id in :orderStatusIds "
        + "AND o.creationDateTime BETWEEN :startDate AND :endDate ORDER BY o.creationDateTime DESC")
    List<Order> findOrdersByCustomerForDates(
        @Param("customer") User customer,
        @Param("orderStatusIds") List<Integer> orderStatusIds,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate);

    @Query(value = """
        SELECT '3 Months', SUM(o.order_value) AS orderTotal FROM `user` AS u
        LEFT JOIN `order` AS o ON o.customer_id = u.id
        AND o.creation_date_time > DATE_SUB(NOW(), INTERVAL 3 MONTH)
        WHERE u.id = :userId
        GROUP BY u.id
        
        UNION
        
        SELECT '6 Months', SUM(o.order_value) AS orderTotal FROM `user` AS u
        LEFT JOIN `order` AS o ON o.customer_id = u.id
        AND o.creation_date_time > DATE_SUB(NOW(), INTERVAL 6 MONTH)
        WHERE u.id = :userId
        GROUP BY u.id
        
        UNION
        
            SELECT '12 Months', SUM(o.order_value) AS orderTotal FROM `user` AS u
            LEFT JOIN `order` AS o ON o.customer_id = u.id
            AND o.creation_date_time > DATE_SUB(NOW(), INTERVAL 12 MONTH)
            WHERE u.id = :userId
            GROUP BY u.id
        """, nativeQuery = true)
    List<Object[]> findAvgOrderValuesForUser(Long userId);

    @Query(value = "SELECT SUM(o.order_value) FROM `order` o WHERE o.customer_id = :userId AND o.creation_date_time BETWEEN DATE_SUB(CURDATE(), INTERVAL :months MONTH) AND CURDATE()", nativeQuery = true)
    Double findXMonthsOrderValue(@Param("userId") Long userId, @Param("months") Integer months);

    @Query(value = "select o from Order o where o.supplier in :suppliers and o.orderStatus.status = :orderReceived and o.creationDateTime > :beforeTwoDays")
    List<Order> findAllOrdersBySuppliersAndStatusAndAfterDate(List<User> suppliers, String orderReceived,
                                                              LocalDateTime beforeTwoDays);

    @Query(value = "select o from Order o where o.creationDateTime >= :start and o.creationDateTime <= :end")
    List<Order> findAllOrdersByDate(LocalDateTime start, LocalDateTime end);

    @Query("select date(o.creationDateTime) as orderDate, count(o.id) as ordersCount, sum(size(o.details)) as"
           +
           " productsCount, "
           +
           "sum(o.orderValue) as totalValue, group_concat(o.id) as orderIds from Order o "
           +
           "where o.customer = :customer and (:query is null or lower(o.supplier.businessName) like CONCAT('%',lower(:query),'%')) "
           +
           "and o.orderStatus.id in :orderStatusIds and o.creationDateTime >= :startDate and  o.creationDateTime <= :endDate "
           +
           "group by date(o.creationDateTime) order by date(o.creationDateTime) desc")
    Page<OrderDateView> findOrderDateViewByCustomerAndOrderStatusAndDateRangeAndGroupByDate(
        @Param("customer") User customer,
        @Param("query") String query,
        @Param("orderStatusIds") List<Integer> orderStatusIds,
        @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate,
        Pageable pageable);

    @Query("select cast(o.creationDateTime as date) as orderDate, count(o.id) as ordersCount, sum(size(o.details)) as"
           +
           " productsCount, "
           +
           "sum(o.orderValue) as totalValue, group_concat(o.id) as orderIds from Order o "
           +
           "where o.supplier = :supplier and (:query is null or lower(o.supplier.businessName) like CONCAT('%',lower"
           +
           "(:query),'%')) "
           +
           "and o.orderStatus.id in :orderStatusIds and o.creationDateTime >= :startDate and  o.creationDateTime <= :endDate "
           +
           "group by cast(o.creationDateTime as date) order by cast(o.creationDateTime as date) desc")
    Page<OrderDateView> findOrderDateViewBySupplierAndOrderStatusAndDateRangeAndGroupByDate(
        @Param("supplier") User supplier,
        @Param("query") String query,
        @Param("orderStatusIds") List<Integer> orderStatusIds,
        @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate,
        Pageable pageable);

}
