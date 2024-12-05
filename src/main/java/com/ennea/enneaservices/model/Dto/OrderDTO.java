package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.OrderCustomerDetails;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private UserSettingsDTO supplier;

    private UserMetadataDTO customer;

    private OrderCustomerDetails customerDetails;

    private OrderStatusDTO orderStatus;

    private double orderValue;

    private LocalDateTime creationDateTime;

    private boolean representativeOrder;

    private UserMetadataDTO representative;

    private String referenceId;

    private List<DetailsDTO> details = new ArrayList<>();

    private String division;

    private String comment;

    private String paymentType;

    private List<ActionDTO> actions = new ArrayList<>();

    private boolean isEditable;

    private double discount;

}
