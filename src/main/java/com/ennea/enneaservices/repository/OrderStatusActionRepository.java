package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.Action;
import com.ennea.enneaservices.model.OrderStatusAction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderStatusActionRepository extends CrudRepository<OrderStatusAction, Integer> {

    @Query("select orderStatusAction.action from OrderStatusAction orderStatusAction where orderStatusAction.currentOrderStatus.id = :status")
    List<Action> findByOrderStatus(int status);

    @Query("select orderStatusAction from OrderStatusAction orderStatusAction where orderStatusAction.currentOrderStatus.id = :status and orderStatusAction.action.id = :actionId")
    Optional<OrderStatusAction> findByOrderStatusAndAction(int status, int actionId);
}
