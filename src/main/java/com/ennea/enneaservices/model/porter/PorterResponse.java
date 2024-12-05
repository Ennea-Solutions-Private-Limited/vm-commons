package com.ennea.enneaservices.model.porter;

import com.ennea.enneaservices.model.porter.createOrder.CreateOrderResponse;
import com.ennea.enneaservices.model.porter.fareEstimate.FareEstimateResponse;
import com.ennea.enneaservices.model.porter.trackOrder.OrderResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PorterResponse {

    private FareEstimateResponse fareEstimateResponse;
    private CreateOrderResponse createOrderResponse;
    private OrderResponse orderResponse;
    private PorterSimpleResponse cancelResponse;
    private PorterSimpleResponse errorResponse;

}
