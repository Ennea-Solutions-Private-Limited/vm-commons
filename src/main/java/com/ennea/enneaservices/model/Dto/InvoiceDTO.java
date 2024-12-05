package com.ennea.enneaservices.model.Dto;

import com.ennea.enneaservices.model.InvoiceStatus;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class InvoiceDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private UserMetadataDTO supplier;

    private UserMetadataDTO customer;

    private String invoiceNumber;

    private LocalDate invoiceDate;

    private double netValue;

    private double pendingAmount;

    private List<InvoiceDetailDTO> details = new ArrayList<>();

    private String partyName;

    private String partyCode;

    private String lrNumber;

    private LocalDate lrDate;

    private String transporter;

    private InvoiceStatus invoiceStatus;

    private LocalDateTime deliveredDateTime;

    private Integer deliveryBoxes;

    private String division;

    private String whatsappStatus;

    private String emailStatus;
}
