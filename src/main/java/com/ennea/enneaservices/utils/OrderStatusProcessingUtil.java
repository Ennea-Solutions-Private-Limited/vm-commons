package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.constants.Constants;
import com.ennea.enneaservices.model.OrderStatus;
import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.repository.OrderStatusRepository;
import com.ennea.enneaservices.repository.OrdersRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class OrderStatusProcessingUtil {


    private final OrderStatusRepository orderStatusRepository;

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrderStatusProcessingUtil(OrderStatusRepository orderStatusRepository, OrdersRepository ordersRepository) {
        this.orderStatusRepository = orderStatusRepository;
        this.ordersRepository = ordersRepository;
    }

    @Transactional
    public void transitionOrders(final List<Long> OrderIds, @NonNull final User distributor) {
        OrderStatus orderStatusInvoicing = orderStatusRepository.findByStatus(Constants.ORDER_PROCESSING);
        OrderStatus orderStatusIntransit = orderStatusRepository.findByStatus(Constants.ORDER_INVOICED);
        ordersRepository.updateOrderStatusBySupplierAndOrderIdsAndOldStatus(distributor.getId(),
                                                                            OrderIds,
                                                                            orderStatusIntransit.getId(),
                                                                            orderStatusInvoicing.getId());
    }
}
