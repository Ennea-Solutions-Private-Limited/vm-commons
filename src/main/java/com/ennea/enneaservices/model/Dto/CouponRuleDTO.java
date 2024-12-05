package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CouponRuleDTO implements Serializable {

    private long id;

    private int totalCoupons;

    private double minOrderValue;

    private int maxCouponsPerDay;

    private int expiryDays;

}
