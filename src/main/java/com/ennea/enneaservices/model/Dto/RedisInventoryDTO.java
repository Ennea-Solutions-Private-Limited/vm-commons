package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class RedisInventoryDTO implements Serializable {

    private List<Long> inventoryIds = new ArrayList<>();
}