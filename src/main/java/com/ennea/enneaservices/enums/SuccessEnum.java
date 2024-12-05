package com.ennea.enneaservices.enums;

public enum SuccessEnum {
    SUCCESS("Success"),
    SUPPLIER_ASSOCIATION_REQUEST_SUCCESS("Supplier association request success"),
    ORDER_PLACEMENT_SUCCESSFUL("Order placement successful"),
    ORDER_PLACEMENT_PARTIAL("Order placement partially successful"),
    PAYMENT_REQUEST_RAISE_SUCCESS("Payment request raise success"),
    USER_REGISTRATION_SUCCESSFUL("User registration successful"),
    USER_AUTO_APPROVAL_SUCCESSFUL("Registration successful, Login with your phone number"),
    REORDER_SUCCESSFUL("Added reorder products to cart"),
    UPDATED_PRODUCT_VISIBILITY("Updated product visibility"),
    PRODUCT_UPDATE_SUCCESSFUL("Product updated successfully"),
    USER_GROUP_UPDATE_SUCCESSFUL("User group update successful"),
    USER_GROUP_DELETE_SUCCESSFUL("User group delete successful"),
    REPRESENTATIVE_ASSOCIATION_UPDATE_SUCCESS("Representative association update success"),
    REPRESENTATIVE_CREATION_SUCCESS("Representatives created successfully"),
    REPRESENTATIVE_ATTENDANCE_UPDATE_SUCCESS("Representative attendance updated successfully"),
    REFERRAL_SUCCESS("Referral submitted successfully"),
    CREATED_SUPPORT_REQUEST("Created support request successfully"),
    USER_PROFILE_UPDATE_SUCCESS("User profile updated successfully"),
    COUPON_CREATION_SUCCESS("Coupons created successfully"),
    OFFER_CREATE_SUCCESS("Offer created successfully"),

    QUOTATION_CREATE_SUCCESS("Quotation created successfully"),

    QUOTATION_UPDATE_SUCCESS("Quotation updated successfully"),

    QUOTATION_ORDER_CREATE_SUCCESS("Quotation order created successfully"),
    QUOTATION_ORDER_UPDATE_SUCCESS("Quotation order updated successfully"),

    BILL_GENERATE_SUCCESS("Bill generated successfully"),
    OFFER_UPDATE_SUCCESS("Offer updated successfully"),
    OFFER_DELETE_SUCCESS("Offer deleted successfully"),
    COUPON_REDEEM_SUCCESS("Coupons redeemed successfully"),
    USER_CREATION_SUCCESSFUL("User creation successful"),
    CUSTOMERS_UPDATION_SUCCESSFUL("Customers updated successfully"),
    CUSTOMER_DELETION_SUCCESSFUL("Customer deleted successfully"),
    CUSTOMER_ASSOCIATION_UPDATE_SUCCESS("Customer association update success"),
    RETURN_CREATION_SUCCESSFUL("Return creation successful"),
    DATA_INGESTION_SUCCESSFUL("Data ingestion successful"),
    INVENTORY_INGESTION_SUCCESSFUL("Inventory ingestion successful"),
    LEDGER_INGESTION_SUCCESSFUL("Ledger ingestion successful"),
    INVENTORY_FILE_UPLOADED(
        "Inventory file has been uploaded successfully, the inventory will be updated in few minutes"),
    LEDGER_FILE_UPLOADED(
        "Ledger file has been uploaded successfully, the ledger will be updated in few minutes"),
    PARTIALLY_INVOICED_ORDERS("Partially invoiced orders"),
    INVOICES_UPLOADED_SUCCESSFULLY("Invoices have been uploaded successfully"),
    INVOICE_UPDATED_SUCCESSFULLY("Invoice has been updated successfully"),
    ALL_ORDERS_INVOICED("All orders invoiced"),
    DETAILS_SUBMITTED_SUCCESSFULLY("Details have been submitted successfully"),
    EMPTY_ORDER_STATUS_UPDATE("No orders to be updated"),
    INVENTORY_UPDATE_SUCESS("Inventory updated successfully"),
    INVENTORY_CREATE_SUCCESS("Inventory created successfully"),
    EMPTY_TRANSACTIONS_UPDATE("No transactions to be updated"),
    MESSAGE_SEND_SUCCESS("Message sent successfully"),
    ORDERS_PRINT_STATUS_UPDATE_SUCCESS("Orders print status updated successfully"),
    ACCOUNT_STATEMENT_REQUESTED("Account statement requested successfully"),
    BENEFICIARY_REGISTRATION_COMPLETE_SUCCESS("Beneficiary registration complete success"),
    BENEFICIARY_REGISTRATION_PARTIAL_SUCCESS("Beneficiary registration partial success"),
    NEW_DISTRIBUTOR_NOTIFICATION_SENT_SUCCESSFULLY("Notification sent successfully"),
    NOTIFICATION_UPDATED_SUCCESSFULLY("Notification updated successfully"),
    DISBURSAL_INITIATED_SUCCESSFULLY("Disbursal initiated successfully"),

    CREDIT_NOTES_UPLOADED_SUCCESSFULLY("Credit notes uploaded successfully"),

    SUBSCRIPTION_CREATED_SUCCESSFULLY("Subscription has been created successfully"),

    SUBSCRIPTION_UPDATED_SUCCESSFULLY("Subscription has been updated successfully"),
    SUBSCRIPTION_DELETED_SUCCESSFULLY("Subscription has been deleted successfully"),

    PAYMENT_UPDATED_SUCCESSFULLY("Payment status has been updated successfully"),
    EMPTY_TRANSACTION_STATUS_UPDATE("No transactions to be updated"),
    SUPPLIER_MAPPING_SUCCESS("Supplier Mapping has been updated successfully"),
    EMPTY_PAYMENT_STATUS_UPDATE("No payments to be updated"),
    PO_UPDATED_SUCCESSFULLY("Purchase Order has been updated successfully"),
    PO_UPDATED_SUCCESSFULLY_REDIRECT_TO_SUPPLIER_MAPPING(
        "Purchase Order has been updated successfully,Redirect to supplierMapping"),
    PRODUCTS_SUGGESTED_SUCCESSFULLY("Products suggested successfully"),

    INVENTORY_MAPPING_UPDATED("Purchase Order inventory mappings have been updated"),
    SUPPLIER_MAPPING_UPDATED("Supplier mappings have been updated"),

    EDIT_SETTING_SUCCESSFULLY("Setting has been updated successfully"),

    LEDGER_PROCESSING_SUCCESSFUL("Ledgers have been processed successfully"),

    ORDER_STATUS_UPDATE_SUCCESSFUL("Order statuses processed successfully"),
    LOCATION_UPDATED_SUCCESSFUL(":Location updated successfully"),

    PRODUCT_SHORTAGE_CREATE_SUCCESS("Product shortage created successfully"),
    PRODUCT_SHORTAGE_DELETE_SUCCESS("Product shortage deleted successfully"),

    ORDER_UPDATE_SUCCESSFUL("Order updated successfully"),
    INTERNAL_INVENTORY_UPDATE_SUCCESS("Internal inventory updated successfully"),

    CREDIT_ENABLEMENT_NOTICE_SENT_SUCCESSFUL("Credit enablement notice sent successfully");

    private final String message;

    SuccessEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}