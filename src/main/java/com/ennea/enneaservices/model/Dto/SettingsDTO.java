package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SettingsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private boolean displayActualFigures;

    private boolean hidePtr;

    private boolean usingHideSchemes;

    private boolean usingExternalApi;

    private boolean automaticApproval;

    private boolean automaticInvoicing;

    private boolean usingOrderMessages;

    private boolean usingHalfSchemes;

    private boolean skipAvailabilityCheck;

    private String representativePrefix;

    private boolean usingValuemediFreeQuantity;

    private boolean paymentsEnabled;

    private boolean manualInventoryUpload;

    private boolean couponBlocked;

    private String sendOrderEmail;

    private boolean usingSaleMode;

    private boolean paymentNotificationsEnabled;
}
 