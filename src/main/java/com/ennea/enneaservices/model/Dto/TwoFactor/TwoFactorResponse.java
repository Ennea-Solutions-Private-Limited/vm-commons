package com.ennea.enneaservices.model.Dto.TwoFactor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwoFactorResponse {

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Details")
    private String details;
}
