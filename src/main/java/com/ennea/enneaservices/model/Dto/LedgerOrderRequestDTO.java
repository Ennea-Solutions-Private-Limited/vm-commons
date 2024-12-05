package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.CartCustomerDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LedgerOrderRequestDTO {

    private List<LedgerOrderRequestInventory> inventory = new ArrayList<>();

    private CartCustomerDetails customerDetails;
}
