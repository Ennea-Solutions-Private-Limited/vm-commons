package com.ennea.enneaservices.model.porter.createOrder;

import lombok.Data;

@Data
public class Instruction {
    // Type of instruction (text is the only supported value for now)
    private String type;

    // Actual delivery instruction description
    private String description;
}
