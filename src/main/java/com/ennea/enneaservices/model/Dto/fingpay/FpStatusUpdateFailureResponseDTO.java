package com.ennea.enneaservices.model.Dto.fingpay;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FpStatusUpdateFailureResponseDTO implements FpStatusUpdateResponseDTO {

    private String error;

}
