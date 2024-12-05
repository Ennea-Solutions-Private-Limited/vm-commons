package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CouponDTO {

    private long id;

    private long rewardAmount;

    private boolean redeemed;

    private LocalDate expiryDate;

}
