package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.OrderPickList;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderPickListRepository extends CrudRepository<OrderPickList, Long> {

    @EntityGraph(attributePaths = {"user"})
    @Query("select orderPickList from OrderPickList orderPickList where orderPickList.user.id = :userId and orderPickList.printed = false")
    List<OrderPickList> findAllOrderSummariesByUserAndPendingPrintStatus(long userId);

    @Modifying
    @Transactional
    @Query("update OrderPickList orderPickList set orderPickList.printed = true "
           + "where orderPickList.user.id = :userId and orderPickList.id in (:ids)")
    void updatePrintStatusByDistributor(long userId, List<Long> ids);
}
