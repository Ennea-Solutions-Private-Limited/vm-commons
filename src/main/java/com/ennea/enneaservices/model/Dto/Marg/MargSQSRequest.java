package com.ennea.enneaservices.model.Dto.Marg;

import lombok.Data;

@Data
public class MargSQSRequest {
    private String erp;
    private Long userId;
    private String ingestionType;
    private MargMastersRequest margMastersRequest;
    private MargOrderStatusDispatchRequest margOrderStatusDispatchRequest;
}
