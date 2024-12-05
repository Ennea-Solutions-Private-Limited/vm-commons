package com.ennea.enneaservices.model.porter.createOrder;

import lombok.Data;

import java.util.List;

@Data
public class DeliveryInstructions {
    // List of delivery instructions
    private List<Instruction> instructionsList;
}
