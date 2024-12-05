package com.ennea.enneaservices.notification;

public interface NotificationConstants {

    String whatsapp = "whatsapp";
    String email = "email";
    String avoidanceString = " part of Avoidance List, Whatsapp notification will not be sent";
    String creditNoteUserGuide = "https://d3njrxqa1u274e.cloudfront.net/PartyAssociationUserGuide_PDF.pdf";
    String placeOrderGuide = "https://d3njrxqa1u274e.cloudfront.net/Place_Order_English.mp4";

    // Template Mappings below

    String stopAckTemplate = "valuemedi_stop_promotions_acknowledgement";
    String startAckTemplate = "valuemedi_start_promotions_acknowledgement";
    String registrationTemplate = "valuemedi_registration_success";
    String credsTemplate = "valuemedi_credentials";
    String credsUpdateTemplate = "valuemedi_credentials_modification";
    String placeOrderGuideTemplate = "valuemedi_new_user_video_demo";
    String creditEnablementTemplate = "valuemedi_credit_enablement_alert";
    String logisticsOrderPaymentTemplate = "valuemedi_logistics_payment";
    String paymentRequest = "valuemedi_payment_request_alert";
    String paymentCollection = "valuemedi_payment_collection";
    String paymentCollectionRetailer = "valuemedi_payment_collection_retailer";
    String paymentDisbursal = "valuemedi_payment_disbursal_alert";
    String creditPaymentConfirmation = "credit_provider_payment_confirmation";
    String creditPaymentDisbursal = "credit_provider_payment_disbursal";
    String productInfoRequest = "valuemedi_product_info_request";
}
