package com.ennea.enneaservices.model.Dto.Marg;

import com.ennea.enneaservices.model.InventoryAvailability;
import com.ennea.enneaservices.model.JobExecutionReport;
import lombok.Data;

import java.util.List;

@Data
public class MargTransactionUpdate {

    private List<InventoryAvailability> inventoryAvailabilityList;

    private JobExecutionReport jobExecutionReport;
}
