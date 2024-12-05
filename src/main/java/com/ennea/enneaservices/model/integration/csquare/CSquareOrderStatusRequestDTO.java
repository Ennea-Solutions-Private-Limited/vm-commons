package com.ennea.enneaservices.model.integration.csquare;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CSquareOrderStatusRequestDTO {

    List<CSquareOrderStatusRequestItemDTO> orderStatus = new ArrayList<>();
}
