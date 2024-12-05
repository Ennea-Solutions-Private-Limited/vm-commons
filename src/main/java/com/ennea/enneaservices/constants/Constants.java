package com.ennea.enneaservices.constants;

public class Constants {

    public static final String SUBSCRIPTION_PENDING = "PENDING";
    public static final String SUBSCRIPTION_PAID = "PAID";
    public static final String ORDER_PROCESSING = "PROCESSING";

    public static final int INVOICE_DUE_DATE_DIFF1 = 1;
    public static final int INVOICE_DUE_DATE_DIFF2 = 2;

    public static final String ORDER_RECEIVED = "RECEIVED";

    public static final String ORDER_APPROVED = "APPROVED";

    public static final String ORDER_CONFIRMED = "CONFIRMED";

    public static final String ORDER_PACKED = "PACKED";

    public static final String ORDER_DELIVERED = "DELIVERED";
    public static final String ORDER_CANCELLED = "CANCELLED";

    public static final String ORDER_INVOICED = "INVOICED";

    public static final String ORDER_RETURNED = "RETURNED";

    public static final String EXPIRED = "EXPIRED";

    public static final String INVOICE_PROCESSING = "PROCESSING";
    public static final String INVOICE_DISPATCHED = "DISPATCHED";
    public static final String INVOICE_DELIVERED = "DELIVERED";

    public static final String ROLE_DISTRIBUTOR = "ROLE_DISTRIBUTOR";

    public static final String ROLE_RETAILER = "ROLE_RETAILER";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String ROLE_SALESMAN = "ROLE_SALESMAN";

    public static final String ROLE_CFA = "ROLE_CFA";

    public static final String ROLE_MANUFACTURER = "ROLE_MANUFACTURER";

    public static final String ROLE_DISTRIBUTOR_SALES = "ROLE_DISTRIBUTOR_SALES";

    public static final String ROLE_DISTRIBUTOR_PURCHASES = "ROLE_DISTRIBUTOR_PURCHASES";

    public static final String ROLE_PENDING = "ROLE_PENDING";

    public static final String ROLE_APPROVER = "ROLE_APPROVER";

    public static final String ROLE_MARKETING_REP = "ROLE_MARKETING_REP";

    public static final String ACTION_DISPATCH = "DISPATCH";
    public static final String ACTION_PRINT = "PRINT";

    public static final String ACTION_APPROVE = "APPROVE";

    public static final String ACTION_DELIVER = "DELIVER";
    public static final String ACTION_CANCEL = "CANCEL";
    public static final String ACTION_CONFIRM = "CONFIRM";
    public static final String ACTION_INVOICE = "INVOICE";

    public static final String CHEMIST_REQUEST_STATUS_SUBMITTED = "SUBMITTED";

    public static final String CHEMIST_REQUEST_STATUS_APPROVED = "APPROVED";

    public static final String CHEMIST_REQUEST_STATUS_DECLINED = "DECLINED";
    public static final String CHEMIST_REQUEST_STATUS_BLOCKED = "BLOCKED";

    public static final String USER_STATUS_PENDING = "PENDING";
    public static final String USER_STATUS_APPROVED = "APPROVED";
    public static final String USER_STATUS_BLOCKED = "BLOCKED";
    public static final String USER_STATUS_DECLINED = "DECLINED";

    public static final String ORDER_TYPE_RETURN = "Return";
    public static final String ORDER_TYPE_SALE = "Sale";

    public static final String OFFER_DISCOUNT = "Discount";
    public static final String OFFER_TOP_UP = "Top Up";

    public static final String NOTIFICATION_TYPE_OFFER = "OFFER";
    public static final String NOTIFICATION_TYPE_QUOTATION = "QUOTATION";
    public static final String NOTIFICATION_TYPE_ORDER = "ORDER";
    public static final String NOTIFICATION_TYPE_SPECIAL_ORDER = "SPECIAL_ORDER";
    public static final String NOTIFICATION_TYPE_RETAIL_ORDER = "RETAIL_ORDER";
    public static final String NOTIFICATION_TYPE_COUPON = "COUPON";

    public static final String NOTIFICATION_TYPE_ASSOCIATION_REQUEST = "ASSOCIATION_REQUEST";
    public static final String NOTIFICATION_TYPE_INVOICE = "INVOICE";

    public static final String NOTIFICATION_TYPE_PAYMENT = "PAYMENT";
    public static final String NOTIFICATION_TYPE_CREDIT_NOTE = "CREDIT_NOTE";

    public static final String NOTIFICATION_TYPE_NEW_DISTRIBUTOR = "NEW_DISTRIBUTOR";

    public static final String NOTIFICATION_ORDER_STATUS_CANCELLED = "Order(s) Cancelled";
    public static final String NOTIFICATION_ORDER_STATUS_INVOICING = "Order(s) Invoicing";
    public static final String NOTIFICATION_ORDER_STATUS_TRANSIT = "Order(s) In-Transit";
    public static final String NOTIFICATION_ORDER_STATUS_RECEIVED = "Order(s) Received";

    public static final String STOP_WORD_TYPE_INVENTORY = "INVENTORY";
    public static final String STOP_WORD_TYPE_SALES = "PURCHASE";
    public static final String STOP_WORD_TYPE_PURCHASE = "SALES";

    public static final String QUOTATION_ORDER_PENDING = "PENDING";
    public static final String QUOTATION_ORDER_APPROVED = "APPROVED";
    public static final String QUOTATION_ORDER_REJECTED = "REJECTED";


    public static final String JOB_TYPE_INVENTORY = "INVENTORY";

    public static final String JOB_TYPE_ORDER = "ORDER";
    public static final String JOB_TYPE_REPLENISH_PURCHASE = "PURCHASE";
    public static final String JOB_TYPE_LIVE_ORDER_STATUS = "ORDER_STATUS";

    public static final String JOB_TYPE_LEDGER = "LEDGER";

    public static final String JOB_TYPE_LEDGER_DIFF = "LEDGER_DIFF";
    public static final String JOB_TYPE_TRANSACTION = "TRANSACTIONS";

    public static final String JOB_TYPE_TRANSACTION_PAYMENTS = "TRANSACTION_PAYMENTS";
    public static final String JOB_TYPE_OUTSTANDING_TRANSACTIONS = "OUTSTANDING_TRANSACTIONS";
    public static final String JOB_TYPE_ORDER_STATUS = "ORDER_STATUS";

    public static final String JOB_STATUS_FAILED = "FAILED";
    public static final String JOB_STATUS_SUCCESS = "SUCCESS";

    public static final String MARG_INVOICE_ORDER_STATUS_INVOICING = "INVOICING";
    public static final String MARG_INVOICE_ORDER_STATUS_IN_TRANSIT = "IN_TRANSIT";

    public static final String TWO_FACTOR_SUCCESS_STATUS = "Success";
    public static final String TWO_FACTOR_ERROR_STATUS = "Error";
    public static final String TWO_FACTOR_OTP_MATCHED = "OTP Matched";
    public static final String TWO_FACTOR_OTP_EXPIRED = "OTP Expired";

    public static final int NAME_START_INDEX = 0;
    public static final int NAME_END_INDEX = 29;

    public static final String NOTIFICATIONS_EMAIL = "notifications@valuemedi.com";
    public static final int DELETED_PRODUCT_AVAILABILITY = 0;
    public static final String MARG_DELETED_PRODUCT_FLAG = "1";
    public static final String JOB_NAME_API_PROFITMAKER_MASTERS = "PROFITMAKER_MASTERS";

    public static final String SUPPORT_EMAIL = "support@valuemedi.com";

    public static final String CART_STATUS = "";

    public static final String REPRESENTATIVE_USERNAME_START_INDEX = "01";
    public static final String USERNAME_START_INDEX = "000010";
    public static final String RETAILER_USERNAME_PREFIX = "R";
    public static final String DISTRIBUTOR_USERNAME_PREFIX = "D";
    public static final String DEFAULT_PASSWORD = "Ennea@123";
    public static final String DISTRIBUTOR_INGESTION_RETAILER_PASSWORD = "vqE84W77@2";
    public static final String CFA_USERNAME_PREFIX = "C";
    public static final String APPROVER_USERNAME_PREFIX = "A";
    public static final String MYSQL_REGEX_PREFIX = "^";
    public static final String MYSQL_REGEX_REPRENSENTATIVE_USERNAME_INDEX_CHAR_COUNT = "[0-9]{2}";
    public static final String MYSQL_REGEX_USERNAME_INDEX_CHAR_COUNT = "[0-9]{6}";

    public static final String RETAILER = "Retailer";
    public static final String CFA_INGESTION_CUSTOMERS = "Customers";
    public static final String CFA_INGESTION_PRODUCTS = "Products";
    public static final String CFA_INGESTION_CFAS = "Cfas";
    public static final String CFA_INGESTION_APPROVERS = "Approvers";
    public static final int PRINT_PRODUCT_LIST_LIMIT = 20;
    public static final int AUTOMATIC_ORDER_TRANSITION_DATE_DIFFERENCE = 0;
    public static final int AUTOMATIC_INVOICING_SLEEP = 60;
    public static final int PRINT_ORDER_DETAILS_SLEEP = 15;

    public static final int DISTRIBUTOR_ORDER_MESSAGE_START_HOUR = 10;
    public static final int DISTRIBUTOR_ORDER_MESSAGE_END_HOUR = 21;

    public static final String COLLECTION_STATUS_PENDING = "Pending";
    public static final String COLLECTION_STATUS_PROCESSING = "Processing";
    public static final String COLLECTION_STATUS_PARTIALLY_PAID = "Partial";
    public static final String COLLECTION_STATUS_CANCELLED = "Cancelled";
    public static final String COLLECTION_STATUS_COMPLETELY_PAID = "Complete";
    public static final String COLLECTION_STATUS_REJECTED = "Rejected";
    public static final String COLLECTION_STATUS_EXPIRED = "Expired";

    public static final String PRODUCT_VISIBILE_STATUS = "VISIBLE";
    public static final String PRODUCT_HIDE_STATUS = "HIDE";

    // Modes
    public static final String SALES = "Sale";
    public static final String PURCHASES = "Purchase";
    public static final String ADMIN = "Admin";

    // Default user details
    public static final String ADDRESS_LINE1 = "";
    public static final String ADDRESS_LINE2 = "";
    public static final int DEFAULT_DISTRICT_ID = 754;
    public static final String DEFAULT_CITY = "";
    public static final String DEFAULT_BUSINESS_NAME = "";

    public static final String OTP_VERIFICATION_STATUS_PENDING = "PENDING";
    public static final String OTP_VERIFICATION_STATUS_SUCCESS = "SUCCESS";
    public static final String OTP_VERIFICATION_STATUS_FAILURE = "FAILURE";
    public static final String OTP_VERIFICATION_STATUS_EXPIRED = "EXPIRED";

    public static final int RECENT_ORDERS_LIMIT = 10;

    public static final boolean ACTIVE_STATUS = true;

    public static final int ANALYTICS_DEFAULT_DURATION_IN_DAYS = 30;

    public static final int ALL_STATUSES = 0;
    public static final int SELF = 0;

    public static final String APPROVAL_REQUEST_STATUS_SUBMITTED = "SUBMITTED";
    public static final String APPROVAL_REQUEST_STATUS_APPROVED = "APPROVED";
    public static final String APPROVAL_REQUEST_STATUS_DECLINED = "DECLINED";
    public static final String APPROVAL_REQUEST_STATUS_OVERRIDDEN = "OVERRIDDEN";
    public static final String APPROVAL_REQUEST_STATUS_REQUEST_REVIEW = "REVIEW";

    public static final String ORDER_SUB_TYPE_NORMAL = "Normal";
    public static final String ORDER_SUB_TYPE_SPECIAL_BONUS = "Special Bonus";
    public static final String ORDER_SUB_TYPE_SPECIAL_RATE = "Special Rate";
    public static final String ORDER_SUB_TYPE_SPECIAL_STANDARD_BONUS = "Special Standard Bonus";

    public static final String CONFIG_DEFAULT_SUFFIX = "DEFAULT";
    public static final String CONFIG_APPROVER_1 = "CONFIG_APPROVER_1";

    public static final String CONFIG_SALESMAN_SEASONAL = "CONFIG_SALESMAN_SEASONAL";
    public static final String CONFIG_SALESMAN_DEFAULT = "CONFIG_SALESMAN_DEFAULT";
    public static final String CONFIG_APPROVER_2 = "CONFIG_APPROVER_2";

    public static final String CONFIG_ADMIN_GROUP = "CONFIG_ADMIN_GROUP";
    public static final String CONFIG_ADMIN_DEFAULT = "CONFIG_ADMIN_DEFAULT";

    public static final String S3_BUCKET_DL = "DL";

    public static final String UNKNOWN_DISTRICT = "Unknown";
    public static final String UNKNOWN_STATE = "Unknown";

    public static final String ORDER_INVOICED_EXTERNAL = "INVOICED";
    public static final String ORDER_PROCESSING_EXTERNAL = "PROCESSING";

    public static final String SECRET_NAME_SEPARATOR = ":::";
    public static final String AWS_SECRET_MANAGER = "aws.secret.manager";
    public static final String SMS_SERVICE = "SMS";
    public static final String EASYPAY_SERVICE = "EASYPAY";
    public static final String APICONNECT_SERVICE = "APICONNECT";
    public static final String FINGPAY_SERVICE = "FINGPAY";

    public static final String MUTHOOT_SERVICE = "MUTHOOT";

    public static final String JDBC_PREFIX = "jdbc:";
    public static final String JDBC_URL_CONCAT = "://";
    public static final String COLON_CONCAT = ":";
    public static final String SLASH_CONCAT = "/";

    public static final String MARG_BILL_FORMAT_TRANSACTION_SPLITTER = "\\r?\\n|\\rT,";
    public static final String MARG_BILL_FORMAT_FOOTER_SPLITTER = "\\r?\\n|\\rF,";

    public static final String MARG_BILL_FORMAT_OTHER_SPLITTER = "\\r?\\n|\\rP,";
    public static final String MARG_BILL_FORMAT_HEADER_SPLITTER = "<MARGERP FORMAT>T,";

    public static final String NEW_ORDER_PLACED_MESSAGE = "New order has been placed";

    public static final String ORDER_INVOICED_DIFFERENTLY = "Order details modified";

    public static final String NEW_SPECIAL_ORDER_PLACED_MESSAGE = "New special order has been placed";
    public static final String NEW_OFFER_ADDED_MESSAGE = "New offer added..!!";
    public static final String NEW_ORDER_RECEIVED_MESSAGE = "New order has been received";

    public static final String NEW_SPECIAL_ORDER_RECEIVED_MESSAGE = "New special order has been received";

    public static final String NEW_RETAIL_ORDER_RECEIVED_MESSAGE = "New retail order has been received";

    public static final String NEW_DISTRIBUTOR_ALERT_MESSAGE = "New Distributor has joined..!!";
    public static final String SPECIAL_ORDER_APPROVED = "Special Order has been approved";

    public static final String SPECIAL_ORDER_CANCELLED = "Special Order has been cancelled";

    public static final String SPECIAL_ORDER_INVOICE_UPLOADED = "Special Order Invoice has been uploaded";
    public static final String NEW_COUPON_MESSAGE = "Congratulations..!!, %s new coupons have been added";
    public static final String ORDER_PENDING_APPROVAL = "Order received for approval";

    public static final String ORDER_OVERRIDDEN = "Order received and update";

    public static final String CFA_DATA_INGESTION_TEMP_DIRECTORY_PREFIX = "data-ingestion";
    public static final String REMINDER_TEMP_DIRECTORY_PREFIX = "reminder-data-ingestion";
    public static final String PARSER_CONFIG_TEMP_DIRECTORY_PREFIX = "parser-config-data-ingestion";
    public static final String DISTRIBUTOR_DATA_INGESTION_TEMP_DIRECTORY_PREFIX = "dist-data-ingestion";
    public static final String PURCHASE_ORDER_DATA_INGESTION_TEMP_DIRECTORY_PREFIX = "purchase-order-data-ingestion";
    public static final String DISTRIBUTOR_INVENTORY_DATA_INGESTION_TEMP_DIRECTORY_PREFIX =
        "dist-inventory-data-ingestion";
    public static final String SUPPLIER_LEDGER_DATA_INGESTION_TEMP_DIRECTORY_PREFIX = "dist-ledger-data-ingestion";
    public static final String SUPPLIER_CUSTOMER_DATA_INGESTION_TEMP_DIRECTORY_PREFIX = "sup-cust-data-ingestion";

    public static final String OUTSTANDING_AMOUNT_DATA_INGESTION_TEMP_DIRECTORY_PREFIX =
        "outstanding-amount-data-ingestion";

    public static final String EMPTY_STRING = "";
    public static final int DRUG_LICENSE_PRESIGNED_URL_EXPIRY_IN_HOURS = 1;

    public static final int DRUG_LICENSE_PRESIGNED_URL_EXPIRY_IN_PROFILE_UPDATE_EMAIL_IN_HOURS = 72;
    public static final int INVENTORY_UPLOAD_PRESIGNED_URL_EXPIRY_IN_HOURS = 12;

    public static final int LEDGER_UPLOAD_PRESIGNED_URL_EXPIRY_IN_HOURS = 12;

    public static final int PRODUCT_SHORTAGE_PDF_PRESIGNED_URL_EXPIRY_IN_HOURS = 24;

    public static final String FILE_TYPE_XLSX = "xlsx";
    public static final String FILE_TYPE_XLS = "xls";

    public static final double DEFAULT_TARGET = 0.0;

    public static final int MAX_S3_KEYS = 1000;

    public static final String CURRENCY = "INR";
    public static final double NO_DISCOUNT = 0;

    public static final String MARG_ERP = "Marg";
    public static final String PROFITMAKER_ERP_CLIENT = "ProfitMaker(Client)";

    public static final String CSQUARE_ERP = "CSquare";
    public static final String FOCUS_ERP = "Focus(Softnet)";
    public static final String PROFITMAKER_ERP_INTEGRATION = "ProfitMaker(Daxinsoft)";
    public static final String EASYSOL_ERP = "EasySol(Excelsior)";
    public static final String METALINKS_ERP = "Metalinks(Client)";
    public static final String ESSEL_ERP_CLIENT = "Essel(Client)";


    public static final int NO_RECORDS_MODIFIED = 0;

    public static final String AXIS_API_CONNECT_SUCCESS = "S";
    public static final String AXIS_API_CONNECT_PDF_STATEMENT = "2";
    public static final String AXIS_API_CONNECT_ACTIVE_STATUS = "ACTIVE";
    public static final String DISBURSAL_STATUS_PENDING = "Pending";
    public static final String DISBURSAL_STATUS_REJECTED = "Rejected";
    public static final String DISBURSAL_STATUS_PROCESSING = "Processing";
    public static final String DISBURSAL_STATUS_DISBURSED = "Disbursed";

    public static final String EASYPAY = "Easypay";
    public static final String FINGPAY = "Fingpay";
    public static final String API_CONNECT = "API_Connect";

    public static final String AXIS_BANK = "Axis Bank Limited";
    public static final String DISBURSAL_MODE_NEFT = "NE";
    public static final String DISBURSAL_MODE_AXIS_INTERNAL = "FT";
    public static final String API_CONNECT_STATUS_PENDING = "Pending";
    public static final String API_CONNECT_STATUS_PROCESSED = "Processed";
    public static final String API_CONNECT_STATUS_RETURN = "Return";
    public static final String API_CONNECT_STATUS_REJECTED = "Rejected";
    public static final String API_CONNECT_TERMINAL_SUCCESS_DESCRIPTION = "Credited to beneficiary";
    public static final String PAYMENT_TYPE_OFFLINE = "OFFLINE";

    public static final String PAYMENT_TYPE_CREDIT = "CREDIT";
    public static final String PAYMENT_TYPE_ONLINE = "ONLINE";

    public static final String OFFER_NOTIFICATION_LINK = "valuemedi://offer/";
    public static final String QUOTATION_NOTIFICATION_LINK = "valuemedi://quotation/";

    public static final String ORDER_NOTIFICATION_LINK = "valuemedi://orderDetails/";
    public static final String NEW_SUPPLIER_NOTIFICATION_LINK = "valuemedi://addSupplier/";
    public static final String COUPONS_NOTIFICATION_LINK = "valuemedi://coupons/";
    public static final String SPECIAL_ORDER_NOTIFICATION_LINK = "valuemedi://specialOrderDetails/";
    public static final String PAYMENT_NOTIFICATION_LINK = "valuemedi://payments/";
    public static final String PAYMENTS_MAIL = "payments@valuemedi.com";

    public static final String PACT_ORDER_STATUS_UPDATE = "pact order status updates";

    public static final String PACT_TRANSACTIONS = "pact transactions";


    public static final String NEW_SCRAPED_ASSOCIATION_MAIL = """
                  <div>
                    Scraped party new association.
                  </div>
                  <ul>
                    <li>Customer BusinessName: %s</li>
                    <li>Customer Phone Number: %s</li>
                    <li>Supplier BusinessName: %s</li>
                    <li>Supplier Phone Number: %s</li>
                  </ul>
        """;

    public static final String NEW_ASSOCIATION_MAIL = """
                      <div>Hi %s,</div>
                      <br />
                      <div>
                        A new party has requested association. The following are the party
                        details :
                      </div>
                      <ul>
                        <li>Party Name: %s</li>
                    <li>Address: %s</li>
                    <li>Phone Number: %s</li>
                  </ul>
                  <div>To update the request, visit https://www.valuemedi.com</div>
                  <br />
                  <div>Thanks,</div>
                  <div>ValueMedi</div>
        """;

    public static final String WHATSAPP_PROMO_FILE_NUMBER = "number";
    public static final String WHATSAPP_PROMO_FILE_TITLE = "title";
    public static final String WHATSAPP_PROMO_FILE_LINK = "link";
    public static final String WHATSAPP_PROMO_FILE_HEADER = "header";
    public static final String WHATSAPP_PROMO_FILE_BODY = "body";
    public static final String WHATSAPP_PROMO_FILE_BUTTON = "button";
    public static final String WHATSAPP_PROMO_TEXT = "text";
    public static final String WHATSAPP_PROMO_IMAGE = "image";
    public static final String WHATSAPP_PROMO_VIDEO = "video";
    public static final String WHATSAPP_PROMO_DOCUMENT = "document";
    public static final String SUCCESS = "SUCCESS";
    public static final String MUTHOOTH_FINCORP_LIMITED = "Muthoot FinCorp Limited";

    public static final String CONSENT_TYPE_CREDIT = "Credit";

    public static final String LOGISTICS_PAYER_SELF = "Self";
    public static final String LOGISTICS_PAYER_PARTY = "Party";
}
