package com.ennea.enneaservices.model.muthoot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class MuthootAPIResponse {

    private MuthootAPIResponseData data;
    private String error;
    private String status;
    private String message;
    private String statusCode;

    @JsonIgnore
    public Boolean isSuccessful() {
        return Boolean.parseBoolean(this.status);
    }

}
