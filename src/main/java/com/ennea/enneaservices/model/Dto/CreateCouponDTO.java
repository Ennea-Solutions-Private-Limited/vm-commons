package com.ennea.enneaservices.model.Dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateCouponDTO {

    @NonNull
    private long customerId;

    @NonNull
    private int value;

}
