package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.CollectionGateway;
import com.ennea.enneaservices.model.PaymentType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentURLDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String url;

    private PaymentType paymentType;

    private CollectionGateway paymentGateway;
}
