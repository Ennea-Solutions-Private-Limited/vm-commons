package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.constants.Constants;
import com.ennea.enneaservices.enums.ErrorEnum;
import com.ennea.enneaservices.exceptions.CustomEnneaExeption;
import com.ennea.enneaservices.model.Dto.ApiError;
import com.ennea.enneaservices.model.Dto.secretsmanager.FingpaySecrets;
import com.ennea.enneaservices.model.Dto.secretsmanager.MuthootSecrets;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@Slf4j
public class SecretsUtil {

    private final ConfigurableEnvironment configurableEnvironment;

    @Autowired
    public SecretsUtil(ConfigurableEnvironment configurableEnvironment) {
        this.configurableEnvironment = configurableEnvironment;
    }

    public FingpaySecrets getFingPaySecrets() {
        final ObjectMapper mapper = new ObjectMapper();
        log.info("Fetching fingpay secrets");
        final String secrets =
            (String) Objects.requireNonNull(configurableEnvironment.getPropertySources()
                                                .get(Constants.AWS_SECRET_MANAGER))
                .getProperty(Constants.FINGPAY_SERVICE);
        try {
            return mapper.readValue(secrets, FingpaySecrets.class);
        } catch (JsonProcessingException e) {
            log.error("Unable to fetch fingpay secrets");
            final ApiError errorResponse =
                new ApiError(HttpStatus.FAILED_DEPENDENCY, ErrorEnum.UNABLE_TO_PARSE_RESPONSE.getMessage(),
                             ErrorEnum.UNABLE_TO_PARSE_RESPONSE.getCode(), null);
            throw new CustomEnneaExeption(errorResponse);
        }
    }

    public MuthootSecrets getMuthootSecrets() {
        final ObjectMapper mapper = new ObjectMapper();
        log.info("Fetching muthoot secrets");
        final String secrets =
            (String) Objects.requireNonNull(configurableEnvironment.getPropertySources()
                                                .get(Constants.AWS_SECRET_MANAGER))
                .getProperty(Constants.MUTHOOT_SERVICE);
        try {
            return mapper.readValue(secrets, MuthootSecrets.class);
        } catch (JsonProcessingException e) {
            log.error("Unable to fetch muthoot secrets");
            final ApiError errorResponse =
                new ApiError(HttpStatus.FAILED_DEPENDENCY, ErrorEnum.UNABLE_TO_PARSE_RESPONSE.getMessage(),
                             ErrorEnum.UNABLE_TO_PARSE_RESPONSE.getCode(), null);
            throw new CustomEnneaExeption(errorResponse);
        }
    }

}
