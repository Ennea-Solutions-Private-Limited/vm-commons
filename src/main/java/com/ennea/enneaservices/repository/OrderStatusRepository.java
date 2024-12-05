package com.ennea.enneaservices.repository;

import com.ennea.enneaservices.model.OrderStatus;
import org.springframework.data.repository.CrudRepository;


public interface OrderStatusRepository extends CrudRepository<OrderStatus, Integer> {

    OrderStatus findByStatus(String status);

}
