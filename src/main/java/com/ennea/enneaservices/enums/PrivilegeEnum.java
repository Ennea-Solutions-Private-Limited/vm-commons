package com.ennea.enneaservices.enums;

import lombok.Getter;

@Getter
public enum PrivilegeEnum {

    //Dashboard
    ANALYTICS("GET", "AnalyticsApi", "getAnalytics", "F101", null),
    ANALYTICS_V2("GET", "AnalyticsApi", "getAnalyticsV2", "F101", null),
    ANALYTICS_DASHBOARD_URL("GET", "AnalyticsApi", "getAnalyticsDashboardUrl", "F101", null),

    //Add to Cart
    CREATE_CART("POST", "NewCartApi", "createCart", "F102", null),
    FETCH_CART("GET", "NewCartApi", "findCartDetails", null, "CREATE_CART"),
    FETCH_APPROVED_SUPPLIERS("GET",
                             "SupplierCustomerApi",
                             "getAllApprovedSupplierCustomers",
                             null,
                             "CREATE_CART,CREATE_RETURN,CREATE_USER_GROUP"),
    FETCH_MUTLIPLE_SUPPLIER_PRODUCTS("GET", "InventoryApi", "findProductListByName", null, "CREATE_CART"),

    FETCH_MUTLIPLE_SUPPLIER_PRODUCT_IDS("GET", "InventoryApi", "findProductListByIds", null, "CREATE_CART"),

    FETCH_MUTLIPLE_SUPPLIER_PRODUCT_SUGGESTIONS("GET", "InventoryApi", "suggestProducts", null, "CREATE_CART"),
    FETCH_GLOBAL_SUPPLIER_IDS("GET", "InventoryApi", "findGlobalSupplierIds", "null", "CREATE_CART"),
    REORDER("GET", "OrderApi", "reorder", null, "CREATE_CART"),
    FETCH_PRODUCTS("GET", "InventoryApi", "findProducts", "F105", null),
    FETCH_INTERNAL_INVENTORY("GET", "InternalInventoryApi", "loadGridData", "F105", null),
    SAVE_INTERNAL_INVENTORY("POST", "InternalInventoryApi", "saveData", "F105", null),
    GROUP_INTERNAL_INVENTORY("GET", "InternalInventoryApi", "processInventoryGrouping", "F105", null),
    COMBINE_INTERNAL_INVENTORY_GROUPS("POST", "InternalInventoryApi", "combineGroups", "F105", null),

    //Cart
    UPDATE_CART("POST", "NewCartApi", "persistCart", "F102", null),
    UPDATE_CART_HOLD("POST", "NewCartApi", "updateCartDetailHold", "F102", null),
    CLEAR_CART("POST", "NewCartApi", "clearCart", "F102", null),
    CREATE_ORDER("POST", "OrderApi", "create", "F103", null),

    CREATE_ORDER_NEW("POST", "OrderApi", "create", "F103", null),

    //Profile
    FETCH_USER_PROFILE("GET", "UserApi", "getUserProfile", "F110,F118", null),
    UPDATE_USER_PROFILE("PUT", "UserApi", "updateUser", "F110,F118", null),
    UPLOAD_PROFILE_IMAGE("PUT", "UserApi", "uploadProfileImage", "F110", null),
    UPDATE_USER_STORE_LOCATION("PUT", "UserLocationApi", "updateUserLocation", "F110", null),
    UPDATE_USER_DRUG_LICENSE("PUT", "UserApi", "updateUserDrugLicense", "F118", null),

    //Switch
    SWITCH_USER_ROLE_MODE("POST", "UserApi", "switchUserRole", "F120,F121", null),
    FETCH_ALL_ASSOCIATED_SUPPLIERS_LIST("GET",
                                        "SupplierRepresentativeApi",
                                        "findAllAssociationsListBySupplierRepresentative",
                                        null,
                                        "SWITCH_USER_ROLE_MODE"),

    //Orders
    FETCH_ORDERS_BY_USER("GET", "OrderApi", "findAllOrdersOfUser", "F103", null),
    GET_ALL_ORDERS_BY_DATE_VIEW("GET", "OrderApi", "getAllOrdersByDateView", "F103", null),
    FETCH_ALL_ORDER_STATUSES("GET",
                             "OrderApi",
                             "getAllStatuses",
                             null,
                             "FETCH_ORDERS_BY_USER"),
    FETCH_ORDER_BY_ID("GET", "OrderApi", "getOrderDetails", null, "FETCH_ORDERS_BY_USER"),
    FETCH_ORDERS_BY_USER_AND_DATES("GET", "OrderApi", "findOrdersByCustomerForDates", null, "FETCH_ORDERS_BY_USER"),
    UPDATE_ORDER_DETAILS("PUT", "OrderApi", "updateOrderDetails", "F103", null),
    UPDATE_ORDER_STATUS("PUT", "OrderApi", "updateOrderStatus", "F103", null),

    //Suppliers
    FETCH_ALL_ASSOCIATED_SUPPLIERS("GET", "SupplierCustomerApi", "getAllSupplierCustomers", "F109", null),
    CREATE_SUPPLIER_ASSOCIATION("POST", "SupplierCustomerApi", "createSupplierCustomer", "F109", null),
    FETCH_ALL_SUPPLIERS("GET", "UserApi", "findSuppliersByQuery", null, "CREATE_SUPPLIER_ASSOCIATION"),
    FETCH_SUGGESTED_SUPPLIERS("GET", "UserApi", "findSuggestedSuppliers", null, "CREATE_SUPPLIER_ASSOCIATION"),

    //Payments
    FETCH_PAYMENTS_BY_USER("GET", "PaymentApi", "getPaymentHistory", "F108", null),
    FETCH_ALL_COLLECTION_STATUSES("GET", "PaymentApi", "getAllCollectionStatuses", "F108",
                                  "FETCH_PAYMENTS_BY_USER"),
    FETCH_ALL_DISBURSAL_STATUSES("GET", "PaymentApi", "getAllDisbursalStatuses", "F108",
                                 "FETCH_PAYMENTS_BY_USER"),
    SUPPLIER_REQUEST_PAYMENT("POST", "PaymentApi", "requestPayment", "F108", null),
    CUSTOMER_PAYMENT("POST", "PaymentApi", "pay", "F108", null),
    UPDATE_PAYMENT("PUT", "PaymentApi", "update", "F108", null),
    FETCH_PAYMENTS_REPORT("GET", "PaymentApi", "getPaymentReportURL", "F108", null),

    // Oustanding
    FETCH_OUTSTANDING("GET", "PaymentApi", "getAllOutstanding", "F107", null),
    SAVE_PAYMENT_NOTIF_SETTING("POST", "SettingsApi", "updatePaymentNotificationSetting", "F108", null),

    //Invoices
    FETCH_INVOICE_BY_ID("GET", "InvoiceApi", "findInvoiceById", "F115", null),
    INVOICES_UPLOAD("POST", "InvoiceApi", "uploadInvoices", "F115", null),
    INVOICE_UPDATE("PUT", "InvoiceApi", "updateInvoiceStatus", "F115", null),

    //Coupons
    FETCH_COUPON_SUMMARY("GET", "CouponApi", "findCouponSummaryByUser", "F116", null),
    SUBMIT_BANK_DETAILS("POST", "UserApi", "addBankDetails", null, "FETCH_COUPON_SUMMARY"),
    FETCH_COUPONS("GET", "CouponApi", "findAllCouponsByUser", null, "FETCH_COUPON_SUMMARY"),
    REDEEM_COUPON("PUT", "CouponApi", "redeemCoupon", "F116", null),

    //Offers
    FETCH_OFFERS("GET", "OfferApi", "findAllOffers", "F106", null),
    CREATE_OFFER("POST", "OfferApi", "createOffer", "F106", null),
    FETCH_INVENTORY_LIST("GET", "InventoryApi", "findProductListByName", null, "CREATE_OFFER"),
    UPDATE_OFFER("PUT", "OfferApi", "updateOffer", "F106", "CREATE_OFFER"),
    DELETE_OFFER("DELETE", "OfferApi", "deleteOffer", "F106", null),

    //Returns
    CREATE_RETURN("POST", "OrderApi", "createReturn", "F104", null),

    //Inventory
    FETCH_INVENTORY_PAGE("GET", "InventoryApi", "findPaginatedProductsByName", "F105", null),
    UPDATE_INVENTORY("PUT", "InventoryApi", "updateProductVisibility", "F105", null),
    UPDATE_INVENTORY_THRESHOLD("PUT", "InventoryApi", "updateInventoryItemThreshold", "F105", null),
    UPDATE_INVENTORY_DETAILS("PUT", "InventoryApi", "updateProductDetails", "F105", null),
    FETCH_ALL_PRODUCTS_OF_ASSOCIATED_SUPPLIERS("GET",
                                               "InventoryApi",
                                               "findAllProducts",
                                               "F102",
                                               null),

    //Groups
    FETCH_USER_GROUPS_PAGE("GET", "UserGroupApi", "getAllUserGroups", "F119", null),
    FETCH_USER_GROUP_BY_ID("GET", "UserGroupApi", "findUserGroup", null, "FETCH_USER_GROUPS_PAGE"),
    DELETE_USER_GROUP_BY_ID("DELETE", "UserGroupApi", "deleteUserGroup", "F119", null),
    CREATE_USER_GROUP("POST", "UserGroupApi", "persistUserGroup", "F119", null),

    //Customers
    FETCH_ALL_ASSOCIATED_CUSTOMERS("GET", "SupplierCustomerApi", "getAllSupplierCustomers", "F113", null),
    FETCH_ALL_REQUEST_STATUSES("GET",
                               "UserApi",
                               "getAllRequestStatuses",
                               null,
                               "FETCH_ALL_ASSOCIATED_CUSTOMERS,FETCH_ALL_ASSOCIATED_REPRESENTATIVES"),
    UPDATE_ASSOCIATED_CUSTOMER("PUT", "SupplierCustomerApi", "updateAssociationStatus", "F113", null),
    UPDATE_ASSOCIATED_LEDGER("PUT", "LedgerApi", "updateSupplierLedger", "F113", null),
    UPDATE_LEDGER_LOCATION("PUT", "LedgerApi", "removeLedgerLocation", "F113", null),
    DELETE_ASSOCIATION("DELETE", "SupplierCustomerApi", "deleteAssociationStatus", "F109", null),

    UPDATE_ALL_ASSOCIATED_CUSTOMERS_STATUS("PUT",
                                           "SupplierCustomerApi",
                                           "updateBlockedStatusOfAllCustomers",
                                           "F113",
                                           null),
    FETCH_LEDGER("GET", "LedgerApi", "findLedgers", null, "UPDATE_ASSOCIATED_CUSTOMER"),
    FETCH_REPRESENTATIVE_LEDGER("GET", "LedgerApi", "findRepresentativeLedgers", "F113", null),
    FETCH_ALL_ASSOCIATED_CUSTOMERS_LIST("GET", "SupplierCustomerApi", "getAllSupplierCustomersList", "F113", null),

    //Representatives
    FETCH_ALL_ASSOCIATED_REPRESENTATIVES("GET",
                                         "SupplierRepresentativeApi",
                                         "findAllAssociationsBySupplier",
                                         "F114",
                                         null),
    FETCH_REPRESENTATIVE_ATTENDANCE("GET",
                                    "SupplierRepresentativeApi",
                                    "getRepresentativeAttendance",
                                    "F114",
                                    null),
    FETCH_REPRESENTATIVE_USER_DETAILS("GET",
                                      "RepresentativeUserApi",
                                      "getAllRepresentativeUsers",
                                      null,
                                      "FETCH_ALL_ASSOCIATED_REPRESENTATIVES"),
    FETCH_ALL_ASSOCIATED_REPRESENTATIVES_LIST("GET",
                                              "SupplierRepresentativeApi",
                                              "findAllAssociationsListBySupplierRepresentative",
                                              null,
                                              "FETCH_ALL_ASSOCIATED_REPRESENTATIVES"),
    UPDATE_ASSOCIATED_REPRESENTATIVE("PUT",
                                     "SupplierRepresentativeApi",
                                     "updateAssociationRequestStatus",
                                     "F114",
                                     null),
    UPDATE_ASSOCIATED_SALESMAN_CODES("PUT",
                                     "SupplierRepresentativeApi",
                                     "updateSalesmanCodes",
                                     null,
                                     "UPDATE_ASSOCIATED_REPRESENTATIVE"),
    UPDATE_ASSOCIATED_REPRESENTATIVE_DETAILS("PUT",
                                             "SupplierRepresentativeApi",
                                             "updateRepresentativeDetails",
                                             null,
                                             "UPDATE_ASSOCIATED_REPRESENTATIVE"),
    FETCH_USER_GROUPS_LIST("GET",
                           "UserGroupApi",
                           "getAllUserGroupsList",
                           null,
                           "UPDATE_ASSOCIATED_REPRESENTATIVE,CREATE_OFFER"),
    CREATE_REPRESENTATIVES("POST", "SupplierRepresentativeApi", "createRepresentatives", "F114", null),

    //Settings
    FETCH_SETTINGS("GET", "SettingsApi", "getInventorySettings", "F111", null),
    UPDATE_USER_SETTINGS("PUT", "SettingsApi", "saveInventorySettings", "F111", null),
    UPDATE_SETTINGS("PUT", "SettingsApi", "updateInventorySettingsWithOtp", null, "UPDATE_USER_SETTINGS"),
    NOTIFY_RETAILERS("POST", "SettingsApi", "sendRetailerNotifications", "F117", null),

    //Admin - User Approval
    FETCH_USERS("GET", "UserApi", "findAllUsers", "F118", null),
    FETCH_ALL_USER_STATUSES("GET", "UserApi", "getAllUserStatuses", null, "FETCH_USERS"),
    CREATE_USER("POST", "UserApi", "createUser", "F118", null),
    FETCH_ALL_ROLES("GET", "UserApi", "findAllRoles", null, "UPDATE_USER_PROFILE"),
    FETCH_ALL_STATES("GET", "UtilityApi", "getAllStates", null, "UPDATE_USER_PROFILE"),
    FETCH_ALL_DISTRICTS("GET", "UtilityApi", "findAllDistrictsByState", null, "UPDATE_USER_PROFILE"),

    //Admin - Coupons
    CREATE_COUPONS("POST", "CouponApi", "createCoupons", "F116", null),
    CREATE_COUPON_FOR_USER("POST", "CouponApi", "createCoupon", null, "CREATE_COUPONS"),
    CREATE_COUPON_RULE("POST", "CouponApi", "createRule", null, "CREATE_COUPONS"),
    CREATE_COUPON_CASES("POST", "CouponApi", "createCases", null, "CREATE_COUPONS"),
    FETCH_COUPON_RULE("GET", "CouponApi", "fetchRule", "F116", null),
    FETCH_COUPON_CASES("GET", "CouponApi", "fetchCases", "F116", null),

    //refresh db
    REFRESH_DB("PUT", "UtilityApi", "refreshDb", "F128", null),
    REFRESH_MARG_MASTERS("PUT", "MargMastersApi", "refreshMargMastersForSupplier", "F128", null),
    CLEAR_MARG_MASTERS("PUT", "MargMastersApi", "clearMargMastersForSupplier", "F128", null),
    RETRIGGER_FAILED_PACT_ORDERS("PUT", "PactApi", "retriggerFailedOrderExport", "F128", null),
    COMPUTE_OUTSTANDING_BALANCE("PUT", "UtilityApi", "computeOutstandingBalance", "F128", null),

    //Admin - Representatives
    CREATE_SUPPLIER_REPRESENTATIVE_ASSOCIATION("POST",
                                               "SupplierRepresentativeApi",
                                               "createSupplierRepresentative",
                                               "F114",
                                               null),
    FETCH_SUPPLIER_REPRESENTATIVE_ASSOCIATIONS("GET",
                                               "SupplierRepresentativeApi",
                                               "findAllAssociationsByRepresentative",
                                               null,
                                               "CREATE_SUPPLIER_REPRESENTATIVE_ASSOCIATION"),
    FETCH_USERS_BY_ROLE("GET", "UserApi", "findUsersByRole", null, "CREATE_SUPPLIER_REPRESENTATIVE_ASSOCIATION"),

    //Admin - ErpCredentials
    FETCH_ERP_CREDENTIALS("GET", "ErpCredentialsApi", "findErpCredentials", "F122", null),
    FETCH_SUPPORTED_ERPS("GET", "ErpCredentialsApi", "findSupportedErp", "F122", "FETCH_ERP_CREDENTIALS"),
    PERSIST_ERP_CREDENTIALS("POST", "ErpCredentialsApi", "persistErpCredentials", "F122", null),

    //Admin - Invoice Parsing
    PARSE_MARG_INVOICES("POST", "InvoiceApi", "parseMargInvoices", "F115", null),

    //Admin - Bulk OnBoarding
    CREATE_BULK_ON_BOARDING("POST", "UserApi", "processDistributorDataIngestion", "F118", null),

    //Distributor - Manual Invoicing
    MANUAL_ORDER_INVOICING("PUT", "OrderApi", "invoiceManually", "F103", null),
    FETCH_CUSTOMERS_WITH_RECEIVED_ORDERS("GET",
                                         "OrderApi",
                                         "findCustomersWithPendingOrders",
                                         null,
                                         "MANUAL_ORDER_INVOICING"),

    //Distributor - Manual Inventory Ingestion.
    MANUAL_INVENTORY_INGESTION("POST", "InventoryApi", "processInventoryDataIngestion", "F105", null),

    //Distributor -Manual Ledger Ingestion
    MANUAL_LEDGER_INGESTION("POST", "LedgerApi", "ledgerDataIngestion", "F105", null),

    //Distributor - Manual SupplierCustomer ingestion
    MANUAL_SUPPLIER_CUSTOMER_INGESTION("POST",
                                       "SupplierCustomerApi",
                                       "ingestSupplierCustomerAssociations",
                                       null,
                                       "CREATE_BULK_ON_BOARDING"),

    // Notifications
    FETCH_ALL_NOTIFICATIONS("GET", "NotificationApi", "getAllNotifications",
                            "F117", null),
    FETCH_NEW_NOTIFICATIONS("GET", "NotificationApi", "getNewNotifications", null,
                            "FETCH_ALL_NOTIFICATIONS"),

    UPDATE_NOTIFICATIONS("PUT", "NotificationApi", "updateAllNotifications", null,
                         "FETCH_ALL_NOTIFICATIONS"),
    UPDATE_NOTIFICATION("PUT", "NotificationApi", "updateNotification", null,
                        "FETCH_ALL_NOTIFICATIONS"),
    UPDATE_NOTIFICATION_TOKEN("PUT", "NotificationApi", "updateNotificationToken", null, "FETCH_ALL_NOTIFICATIONS"),

    //Sub user login
    FETCH_USER_TOKEN("GET", "UserApi", "getAutoLoginTokenForUser", "F123", null),

    //Retail_inventory
    CREATE_INVENTORY("POST", "InventoryApi", "addInventory", "F124", null),
    UPDATE_RETAIL_INVENTORY("PUT", "InventoryApi", "updateInventory", "F124", null),
    GET_INVENTORY("GET", "InventoryApi", "findPaginatedProductsByName", "F124", null),

    //Retail_billing
    GET_RETAIL_BILLS("GET", "RetailBillingApi", "findAllBillsByUser", "F125", null),
    GET_RETAIL_BILL_DETAILS("GET", "RetailBillingApi", "getBillDetails", null, "GET_RETAIL_BILLS"),
    CREATE_RETAIL_BILL("POST", "RetailBillingApi", "createBill", "F125", null),

    //Retail_customer
    GET_RETAIL_CUSTOMERS("GET", "RetailCustomerApi", "getAllRetailCustomers", "F126", null),
    GET_RETAIL_CUSTOMERS_LIST("GET", "RetailCustomerApi", "getAllRetailCustomersList", null, "GET_RETAIL_CUSTOMERS"),
    CREATE_RETAIL_CUSTOMERS("POST", "RetailCustomerApi", "addRetailCustomers", "F126", null),
    UPDATE_RETAIL_CUSTOMERS("PUT", "RetailCustomerApi", "updateRetailCustomers", "F126", null),

    DELETE_RETAIL_CUSTOMER("DELETE", "RetailCustomerApi", "deleteRetailCustomer", "F126", null),

    //retail_order
    GET_ALL_RETAIL_ORDERS("GET", "RetailOrderApi", "findAllRetailOrders", "F129", null),
    GET_ALL_RETAIL_ORDER_STATUSES("GET", "RetailOrderApi", "getAllRetailOrderStatuses", null, "GET_ALL_RETAIL_ORDERS"),
    GET_RETAIL_ORDER_DETAILS("GET", "RetailOrderApi", "getRetailOrderDetails", null, "GET_ALL_RETAIL_ORDERS"),
    UPDATE_RETAIL_ORDER_STATUS("PUT", "RetailOrderApi", "updateRetailOrderStatus", "F129", null),
    SEND_PRESCRIPTION_URL("GET", "RetailCustomerApi", "sendPrescriptionUrl", null, "GET_RETAIL_CUSTOMERS"),

    //CreditNotes repository
    GET_ALL_CREDIT_NOTES("GET", "CreditNoteApi", "getAllCreditNotes", "F132", null),
    CREATE_CREDIT_NOTES("POST", "CreditNoteApi", "uploadCreditNotes", "F132", null),

    //Subscriptions Permissions
    GET_ALL_SUBSCRIPTIONS("GET", "SubscriptionsApi", "getUserSubscriptions", "F131", null),
    DELETE_SUBSCRIPTIONS("DELETE", "SubscriptionsApi", "deleteUserSubscription", "F131", null),
    UPDATE_SUBSCRIPTIONS("PUT", "SubscriptionsApi", "updateUserSubscription", "F131", null),
    CREATE_SUBSCRIPTIONS("POST", "SubscriptionsApi", "createUserSubscription", "F131", null),

    //disbursal
    GET_BALANCE("GET", "DisbursalApi", "getBalance", "F130", null),
    GET_STATEMENT("GET", "DisbursalApi", "getStatement", "F130", null),
    REGISTER_BENEFICIARY("POST", "DisbursalApi", "registerBeneficiary", "F130", null),
    GET_BENEFICIARY("GET", "DisbursalApi", "fetchBeneficiary", "F130", null),
    FETCH_PENDING_DISBURSALS("GET", "DisbursalApi", "fetchPendingDisbursals", "F130", null),
    CREATE_FUND_DISBURAL("POST", "DisbursalApi", "initiateFundDisbursal", "F130", null),
    UPDATE_FUND_DISBURSAL_STATUS("PUT", "DisbursalApi", "updateDisbursalStatusesByUser", "F130", null),

    //Purchase Order Permissions
    UPLOAD_PURCHASE_ORDER("POST", "PurchaseOrderApi", "uploadPOSheet", "F102", null),
    GET_PO_SUPPLIER_MAPPING("GET", "PurchaseOrderApi", "getSupplierMapping", null, "CREATE_CART"),
    GET_PO_INVENTORY_MAPPING("GET", "PurchaseOrderApi", "getInventoryMapping", null, "CREATE_CART"),
    UPDATE_PO_SUPPLIER_MAPPING("PUT", "PurchaseOrderApi", "updateSupplierMapping", null, "CREATE_CART"),
    UPDATE_PO_INVENTORY_MAPPING("PUT", "PurchaseOrderApi", "updateInventoryMapping", null, "CREATE_CART"),
    UPDATE_PO_DETAIL("PUT", "PurchaseOrderApi", "updatePurchaseOrderDetail", null, "CREATE_CART"),
    UPDATE_PO_INVENTORY_MAPPING_WITHOUT_SUPPLIER("POST",
                                                 "PurchaseOrderApi",
                                                 "insertInventoryMapping",
                                                 null,
                                                 "CREATE_CART"),
    GET_PURCHASE_ORDER_DETAILS("GET", "PurchaseOrderApi", "getPurchaseOrderDetails", null, "CREATE_CART"),
    DELETE_PURCHASE_ORDER_DETAILS("DELETE", "PurchaseOrderApi", "deletePurchaseOrderDetails", null, "CREATE_CART"),

    // Pre Cart list apis

    DELETE_PRE_CART_ITEMS("DELETE", "PreCartApi", "deletePreCartItems", null, "CREATE_CART"),
    GET_PRE_CART_ITEMS("GET", "PreCartApi", "getPreCarts", null, "CREATE_CART"),
    UPDATE_PRECART_ITEMS("POST", "PreCartApi", "updatePreCartItems", null, "CREATE_CART"),
    CREATE_PRE_CART_ITEMS("POST", "PreCartApi", "createPreCarts", null, "CREATE_CART"),
    PRE_CART_ADD_TO_CART("POST", "PreCartApi", "addPreCartToCart", null, "CREATE_CART"),

    // Broadcasts
    UPLOAD_BROADCASTS("POST", "BroadcastApi", "uploadBroadcastSheet", "F133", null),
    GET_BROADCASTS("GET", "BroadcastApi", "getBroadcasts", "F133", null),
    GET_BROADCASTS_TEMPLATES("GET", "BroadcastApi", "getBroadcastTemplates", "F133", null),
    GET_BROADCASTS_LIMIT("GET", "BroadcastApi", "getRemainingLimitByUser", "F133", null),
    UPDATE_BROADCASTS_LIMIT("POST", "BroadcastApi", "updateRemainingLimitByUser", "F133", null),

    // Parser Config
    SAVE_PARSER_CONFIG("POST",
                       "ParserConfigApi",
                       "saveParserConfig",
                       null,
                       "UPLOAD_BROADCASTS,CREATE_CREDIT_NOTES,INVOICES_UPLOAD,UPLOAD_PURCHASE_ORDER,MANUAL_INVENTORY_INGESTION"),
    FETCH_PARSER_CONFIG_JSON("GET",
                             "ParserConfigApi",
                             "getUserTemplate",
                             null,
                             "UPLOAD_BROADCASTS,CREATE_CREDIT_NOTES,INVOICES_UPLOAD,UPLOAD_PURCHASE_ORDER,MANUAL_INVENTORY_INGESTION"),
    FETCH_FILE_HEADER("POST",
                      "ParserConfigApi",
                      "getFileHeaders",
                      null,
                      "UPLOAD_BROADCASTS,CREATE_CREDIT_NOTES,INVOICES_UPLOAD,UPLOAD_PURCHASE_ORDER,MANUAL_INVENTORY_INGESTION"),

    // Quotation Permissions
    GET_ALL_QUOTATIONS("GET", "QuotationApi", "getAllQuotations", "F135", null),
    GET_QUOTATION_DETAILS("GET", "QuotationApi", "getQuotationDetails", "F135", null),
    UPDATE_QUOTATION("PUT", "QuotationApi", "deactivateQuotation", "F135", null),
    CREATE_QUOTATION("POST", "QuotationApi", "createQuotation", "F135", null),
    CREATE_QUOTATION_ORDER("POST", "QuotationApi", "createQuotationOrder", null, "UPDATE_QUOTATION"),
    UPDATE_QUOTATION_ORDER("PUT", "QuotationApi", "updateQuotationOrder", null, "UPDATE_QUOTATION"),

    FETCH_USER_SETTINGS("GET", "UserPreferenceApi", "fetchPreferences", "F136", null),

    GET_CREDIT_PROVIDERS("GET", "CreditProviderApi", "getAllCreditProviders", "F108", null),

    FETCH_CREDIT_DETAILS("GET", "CreditProviderApi", "getCreditDetails", "F108", null),

    CREATE_CREDIT_PAYMENT("POST", "CreditProviderApi", "createCreditPayment", "F108", null),

    GET_ALL_CREDIT_PROVIDERS("GET", "CreditProviderApi", "fetchAllCreditProviders", "F108", null),

    GET_CREDIT_REDIRECT("GET", "CreditProviderApi", "getRedirection", "F108", null),
    GET_USER_CREDIT_GRID("GET", "CreditProviderApi", "getCreditGridForUser", "F108", null),

    SAVE_USER_CONSENT("POST", "UserConsentApi", "saveConsent", "F108", null),
    FETCH_USER_CONSENT("GET", "UserConsentApi", "fetchConsent", "F108", null),

    // Location
    GET_USER_LOCATION("GET", "UserLocationApi", "getUserLocation", "F137", null),

    SAVE_USER_SETTING("POST", "UserPreferenceApi", "savePreference", "F136", null),

    // Logistics
    GET_LOGISTICS_GRID("GET", "LogisticsApi", "getGrid", "F138", null),
    GET_LOGISTICS_USER_OPTIONS("GET", "LogisticsApi", "getUserOptions", "F138", null),
    GET_LOGISTICS_QUOTES("GET", "LogisticsApi", "getQuotes", "F138", null),
    CREATE_LOGISTICS_ORDER("POST", "LogisticsApi", "createOrder", "F138", null),
    CREATE_LOGISTICS_ORDER_PAYMENT("POST", "LogisticsApi", "getPaymentLink", "F138", null),
    CANCEL_LOGISTICS_ORDER("GET", "LogisticsApi", "cancelOrder", "F138", null),

    // Product Shortages
    GET_PRODUCT_SHORTAGE("GET", "ProductShortageApi", "getProductShortages", "F139", null),
    CREATE_PRODUCT_SHORTAGE("POST", "ProductShortageApi", "createProductShortage", "F139", null),
    UPLOAD_PRODUCT_SHORTAGE("POST", "ProductShortageApi", "uploadProductShortages", "F139", null),
    UPLOAD_SINGLE_PRODUCT_SHORTAGE("POST", "ProductShortageApi", "uploadProductImage", "F139", null),
    UPDATE_PRODUCT_SHORTAGE("PUT", "ProductShortageApi", "updateProductShortage", "F139", null),
    DELETE_PRODUCT_SHORTAGE("DELETE", "ProductShortageApi", "deleteProductShortage", "F139", null),
    GENERATE_PRODUCT_SHORTAGE_PDF("POST", "ProductShortageApi", "generateShortagePDF", "F139", null),

    // User Mapped Numbers
    LIST_MAPPED_NUMBERS("GET", "UserNumberMappingApi", "listMappedNumbers", "F110", null),
    MAP_NUMBER_TO_USER("POST", "UserNumberMappingApi", "mapNumberToUser", "F110", null),
    DELETE_MAPPED_NUMBER("DELETE", "UserNumberMappingApi", "deleteMappedNumber", "F110", null),
    REMOVE_MAPPED_NUMBER_SESSION("DELETE", "UserNumberMappingApi", "removeMappedNumberSession", "F110", null),
    REMOVE_ALL_MAPPED_NUMBER_SESSIONS("DELETE", "UserNumberMappingApi", "removeAllMappedNumberSessions", "F110", null),

    // Product Info
    GET_PRODUCT_INFO("GET", "ProductApi", "findPaginatedProductsByName", "F141", null),
    FIND_PRODUCT("POST", "ProductApi", "findProductsBySlugs", "F141", null),
    SEND_EMAIL("POST", "ProductApi", "getSuggestedProduct", "F141", null);

    private final String type;
    private final String className;
    private final String methodName;
    private final String privilegeCode;
    private final String parentPrivilege;

    PrivilegeEnum(String type, String className, String methodName, String code, String parentPrivilege) {
        this.type = type;
        this.className = className;
        this.methodName = methodName;
        this.privilegeCode = code;
        this.parentPrivilege = parentPrivilege;
    }

}
