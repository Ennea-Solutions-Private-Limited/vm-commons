package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CouponCaseDTO implements Serializable {

    private long id;

    private int couponValue;

    private int probability;

}
