package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.enums.ErrorEnum;
import com.ennea.enneaservices.exceptions.CustomEnneaExeption;
import com.ennea.enneaservices.model.Dto.ApiError;
import com.ennea.enneaservices.model.Order;
import com.ennea.enneaservices.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExceptionUtils {

    public static void throwForbiddenOrderFetch(Order order, User user) {
        log.warn("User : {} forbidden from fetching order : {}", user.getId(), order.getId());
        final ApiError apiError = new ApiError(HttpStatus.FORBIDDEN,
                                               ErrorEnum.FORBIDDEN_ORDER_FETCH.getMessage(),
                                               ErrorEnum.FORBIDDEN_ORDER_FETCH.getCode(),
                                               null);
        throw new CustomEnneaExeption(apiError);
    }

    public static void throwNotAValidOrder(long orderId, User user) {
        log.warn("Unable to find order : {} for user: {}", orderId, user.getId());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ErrorEnum.NOT_A_VALID_ORDER.getMessage(),
                                               ErrorEnum.NOT_A_VALID_ORDER.getCode(), null);
        throw new CustomEnneaExeption(apiError);
    }

    public static void throwNotAValidQuotation(long quotationId, User user) {
        log.error("Unable to find quotation : {} for user: {}", quotationId, user.getId());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ErrorEnum.UNABLE_TO_FIND_QUOTATION.getMessage(),
                                               ErrorEnum.UNABLE_TO_FIND_QUOTATION.getCode(), null);
        throw new CustomEnneaExeption(apiError);
    }
}
