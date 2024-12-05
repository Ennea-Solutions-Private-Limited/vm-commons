package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.constants.Constants;
import com.ennea.enneaservices.model.EmailStatus;
import com.ennea.enneaservices.model.Payment;
import com.ennea.enneaservices.model.PaymentCustomerDetails;
import com.ennea.enneaservices.model.PaymentInvoiceAmount;
import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.model.WhatsappStatus;
import com.ennea.enneaservices.model.whatsapp.WhatsappTemplateComponent;
import com.ennea.enneaservices.model.whatsapp.WhatsappTemplateMessage;
import com.ennea.enneaservices.model.whatsapp.WhatsappTemplateParameterText;
import com.ennea.enneaservices.notification.NotificationConstants;
import com.ennea.enneaservices.notification.NotificationPayload;
import com.ennea.enneaservices.notification.email.BaseEmailTemplate;
import com.ennea.enneaservices.repository.EmailStatusRepository;
import com.ennea.enneaservices.repository.WhatsappStatusRepository;
import lombok.Data;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PaymentSummaryUtils {

    private static final DateTimeFormatter stdFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final WhatsappStatusRepository whatsappStatusRepository;

    private final EmailStatusRepository emailStatusRepository;

    @Autowired
    public PaymentSummaryUtils(WhatsappStatusRepository whatsappStatusRepository,
                               EmailStatusRepository emailStatusRepository) {
        this.whatsappStatusRepository = whatsappStatusRepository;
        this.emailStatusRepository = emailStatusRepository;
    }

    public static String buildAdminMailBody(@NonNull final Payment payment, @NonNull final Set<String> invoiceNumbers) {
        StringBuilder message = new StringBuilder();
        message.append("<h4 style='margin:0;'> Payment Id : ").append(payment.getId()).append("</h4>");
        message.append("<h4 style='margin:0;'> Supplier UUID : ")
            .append(payment.getSupplier().getUuid())
            .append("</h4>");
        message.append("<h4 style='margin:0;'> Supplier Name : ")
            .append(payment.getSupplier().getBusinessName())
            .append("</h4>");
        if(Objects.nonNull(payment.getRepresentative())){
            message.append("<h4 style='margin:0;'> Representative : ")
                .append(payment.getRepresentative().getContactPerson())
                .append("</h4>");
        }
        message.append("<h4 style='margin:0;'> Customer UUID : ")
            .append(payment.getCustomer().getUuid())
            .append("</h4>");
        message.append("<h4 style='margin:0;'> Customer : ")
            .append(payment.getCustomer().getBusinessName())
            .append("</h4>");
        message.append("<h4 style='margin:0;'> Requested Amount : ")
            .append(payment.getRequestedAmount())
            .append("</h4>");
        message.append("<h4 style='margin:0;'> Requested Date : ")
            .append(payment.getCreationDateTime().toLocalDate())
            .append("</h4>");
        message.append("<h4 style='margin:0;'> Collection Status : ").append(payment.getCollectionMetadata()
                                                                                 .getStatus()
                                                                                 .getName()).append("</h4>");
        if(payment.getCollectionMetadata()
               .getStatus()
               .getName()
               .equalsIgnoreCase(Constants.COLLECTION_STATUS_PARTIALLY_PAID) ||
           payment.getCollectionMetadata()
               .getStatus()
               .getName()
               .equalsIgnoreCase(Constants.COLLECTION_STATUS_COMPLETELY_PAID)){
            message.append("<h4 style='margin:0;'> Paid Amount : ").append(payment.getPaidAmount()).append("</h4>");
            message.append("<h4 style='margin:0;'> Paid Date : ").append(payment.getCollectionMetadata()
                                                                             .getModificationDateTime()
                                                                             .toLocalDate()).append("</h4>");
            message.append("<h4 style='margin:0;'> Mode : ")
                .append(payment.getCollectionMetadata().getMode())
                .append("</h4>");
            message.append("<h4 style='margin:0;'> Invoices : ")
                .append(String.join(",", invoiceNumbers))
                .append("</h4>");
        }
        return message.toString();
    }

    public static String buildSupplierMailBody(@NonNull final Payment payment, @NonNull final String partyName,
                                               @NonNull final Set<String> invoiceNumbers) {
        StringBuilder message = new StringBuilder();
        message.append("<h4 style='margin:0;'> Payment Id : ").append(payment.getId()).append("</h4>");
        if(Objects.nonNull(payment.getRepresentative())){
            message.append("<h4 style='margin:0;'> Representative : ")
                .append(payment.getRepresentative().getContactPerson())
                .append("</h4>");
        }
        if(payment.getCustomer() != null){
            message.append("<h4 style='margin:0;'> Customer UUID : ")
                .append(payment.getCustomer().getUuid())
                .append("</h4>");
        }
        message.append("<h4 style='margin:0;'> Customer : ").append(partyName).append("</h4>");
        message.append("<h4 style='margin:0;'> Customer Party code: ")
            .append(payment.getCustomerDetails().getPartyCode())
            .append("</h4>");
        message.append("<h4 style='margin:0;'> Requested Amount : ")
            .append(payment.getRequestedAmount())
            .append("</h4>");
        message.append("<h4 style='margin:0;'> Requested Date : ")
            .append(payment.getCreationDateTime().toLocalDate())
            .append("</h4>");
        message.append("<h4 style='margin:0;'> Collection Status : ").append(payment.getCollectionMetadata()
                                                                                 .getStatus()
                                                                                 .getName()).append("</h4>");
        message.append("<h4 style='margin:0;'> Paid Amount : ").append(payment.getPaidAmount()).append("</h4>");
        message.append("<h4 style='margin:0;'> Paid Date : ").append(payment.getCollectionMetadata()
                                                                         .getModificationDateTime()
                                                                         .toLocalDate()).append("</h4>");
        message.append("<h4 style='margin:0;'> Mode : ")
            .append(payment.getCollectionMetadata().getMode())
            .append("</h4>");
        message.append("<h4 style='margin:0;'> Invoices : ")
            .append(String.join(",", invoiceNumbers))
            .append("</h4>");
        return message.toString();
    }

    public NotificationPayload buildPayloadForRequest(@NonNull final Payment payment) {
        PaymentNotificationPreferences preference = new PaymentNotificationPreferences(payment.getCustomerDetails());

        final String templateName = NotificationConstants.paymentRequest;

        HashMap<WhatsappTemplateMessage, WhatsappStatus> whatsappMap = new HashMap<>();
        HashMap<BaseEmailTemplate, EmailStatus> emailMap = new HashMap<>();

        if(preference.isWhatsappEnabled()){
            WhatsappTemplateMessage message =
                new WhatsappTemplateMessage(payment.getCustomerDetails().getPhoneNumber().toString(), templateName);

            WhatsappTemplateComponent bodyComponent =
                new WhatsappTemplateComponent(WhatsappTemplateComponent.Type.body, null, null);
            bodyComponent.getParameters()
                .add(new WhatsappTemplateParameterText(payment.getCustomerDetails().getBusinessName()));
            bodyComponent.getParameters()
                .add(new WhatsappTemplateParameterText(payment.getSupplier().getBusinessName()));
            bodyComponent.getParameters()
                .add(new WhatsappTemplateParameterText(String.valueOf(payment.getRequestedAmount())));

            message.getTemplate().getComponents().add(bodyComponent);

            WhatsappStatus whatsappStatus =
                new WhatsappStatus(payment.getCustomerDetails().getPhoneNumber(), templateName, payment.getSupplier());
            whatsappStatusRepository.save(whatsappStatus);
            whatsappMap.put(message, whatsappStatus);
        }

        if(preference.isEmailEnabled()){
            BaseEmailTemplate mail = new BaseEmailTemplate();
            mail.setTo(payment.getCustomerDetails().getEmailId());
            mail.setTemplate(templateName);
            HashMap<String, String> templateValues = new HashMap<>();
            templateValues.put("retailer", payment.getCustomerDetails().getBusinessName());
            templateValues.put("supplier", payment.getSupplier().getBusinessName());
            templateValues.put("amount", String.valueOf(payment.getRequestedAmount()));
            mail.setMap(templateValues);

            EmailStatus emailStatus =
                new EmailStatus(payment.getCustomerDetails().getEmailId(), templateName, payment.getSupplier());
            emailStatusRepository.save(emailStatus);
            emailMap.put(mail, emailStatus);
        }

        return new NotificationPayload(null, emailMap, whatsappMap);
    }

    public NotificationPayload buildPayloadForCollection(@NonNull final Payment payment,
                                                         final boolean isRepresentative) {
        PaymentNotificationPreferences preference = new PaymentNotificationPreferences(
            new PaymentCustomerDetails(payment.getSupplier()));

        boolean isPaymentNotificationEnabled =
            payment.getSupplier().getSettings().getPaymentSettings().isPaymentNotificationsEnabled();

        final String supplierTemplate = NotificationConstants.paymentCollection;
        final String retailerTemplate = NotificationConstants.paymentCollectionRetailer;

        HashMap<WhatsappTemplateMessage, WhatsappStatus> whatsappMap = new HashMap<>();
        HashMap<BaseEmailTemplate, EmailStatus> emailMap = new HashMap<>();

        final Set<String> invoiceNumbers = payment.getInvoiceAmounts()
            .stream()
            .map(PaymentInvoiceAmount::getInvoiceNumber)
            .collect(Collectors.toSet());

        final String invoiceNumberStr = String.join(",", invoiceNumbers);

        String header = "Collected";
        String retailer = payment.getCustomerDetails().getBusinessName();
        if(isRepresentative){
            retailer += " by " + payment.getRepresentative().getContactPerson();
        }

        String collectionDate = payment.getCollectionMetadata().getModificationDateTime().format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        String collectionDateDetails = collectionDate;

        if(!invoiceNumbers.isEmpty()){
            collectionDateDetails += " against the following invoices" + invoiceNumberStr;
        }

        if(isPaymentNotificationEnabled){

            if(preference.isWhatsappEnabled()){
                WhatsappTemplateMessage supplierMessage =
                    new WhatsappTemplateMessage(payment.getSupplier().getPhoneNumber().toString(), supplierTemplate);

                WhatsappTemplateComponent
                    supplierHeaderComponent =
                    new WhatsappTemplateComponent(WhatsappTemplateComponent.Type.header, null, null);
                supplierHeaderComponent.getParameters().add(new WhatsappTemplateParameterText(header));

                supplierMessage.getTemplate().getComponents().add(supplierHeaderComponent);

                WhatsappTemplateComponent supplierBodyComponent =
                    new WhatsappTemplateComponent(WhatsappTemplateComponent.Type.body, null, null);
                supplierBodyComponent.getParameters()
                    .add(new WhatsappTemplateParameterText(payment.getSupplier().getBusinessName()));
                supplierBodyComponent.getParameters()
                    .add(new WhatsappTemplateParameterText(String.valueOf(payment.getPaidAmount()
                                                                          + payment.getWalletAmount())));
                supplierBodyComponent.getParameters()
                    .add(new WhatsappTemplateParameterText(retailer));
                supplierBodyComponent.getParameters().add(new WhatsappTemplateParameterText(collectionDateDetails));

                supplierMessage.getTemplate().getComponents().add(supplierBodyComponent);

                WhatsappStatus supplierWhatsappStatus =
                    new WhatsappStatus(payment.getSupplier().getPhoneNumber(), supplierTemplate, payment.getCustomer());
                whatsappStatusRepository.save(supplierWhatsappStatus);
                whatsappMap.put(supplierMessage, supplierWhatsappStatus);
            }

            if(payment.getCustomerDetails().getPhoneNumber() != null){
                WhatsappTemplateMessage retailerMessage =
                    new WhatsappTemplateMessage(payment.getCustomer().getPhoneNumber().toString(), retailerTemplate);

                WhatsappTemplateComponent
                    retailerHeaderComponent =
                    new WhatsappTemplateComponent(WhatsappTemplateComponent.Type.header, null, null);
                retailerHeaderComponent.getParameters().add(new WhatsappTemplateParameterText(header));

                retailerMessage.getTemplate().getComponents().add(retailerHeaderComponent);

                WhatsappTemplateComponent retailerBodyComponent =
                    new WhatsappTemplateComponent(WhatsappTemplateComponent.Type.body, null, null);
                retailerBodyComponent.getParameters()
                    .add(new WhatsappTemplateParameterText(payment.getCustomer().getBusinessName()));
                retailerBodyComponent.getParameters()
                    .add(new WhatsappTemplateParameterText(String.valueOf(payment.getPaidAmount()
                                                                          + payment.getWalletAmount())));
                retailerBodyComponent.getParameters()
                    .add(new WhatsappTemplateParameterText(payment.getRepresentative().getContactPerson()));
                retailerBodyComponent.getParameters()
                    .add(new WhatsappTemplateParameterText(payment.getSupplier().getBusinessName()));
                retailerBodyComponent.getParameters().add(new WhatsappTemplateParameterText(collectionDate));
                retailerBodyComponent.getParameters().add(new WhatsappTemplateParameterText(invoiceNumberStr));

                retailerMessage.getTemplate().getComponents().add(retailerBodyComponent);

                WhatsappStatus retailerWhatsappStatus =
                    new WhatsappStatus(payment.getCustomerDetails().getPhoneNumber(),
                                       retailerTemplate,
                                       payment.getSupplier());
                whatsappStatusRepository.save(retailerWhatsappStatus);
                whatsappMap.put(retailerMessage, retailerWhatsappStatus);
            }

        }

        if(preference.isEmailEnabled()){
            BaseEmailTemplate mail = new BaseEmailTemplate();
            mail.setTo(payment.getSupplier().getEmailId());
            mail.setTemplate(supplierTemplate);
            HashMap<String, String> templateValues = new HashMap<>();
            templateValues.put("subject", header);
            templateValues.put("supplier", payment.getSupplier().getBusinessName());
            templateValues.put("amount", String.valueOf(payment.getPaidAmount()));
            templateValues.put("retailer", retailer);
            templateValues.put("dateDetail", collectionDateDetails);
            mail.setMap(templateValues);

            EmailStatus emailStatus =
                new EmailStatus(payment.getSupplier().getEmailId(), supplierTemplate, payment.getCustomer());
            emailStatusRepository.save(emailStatus);
            emailMap.put(mail, emailStatus);
        }

        return new NotificationPayload(null, emailMap, whatsappMap);
    }

    public NotificationPayload buildPayloadForDisbursal(@NonNull final Payment payment) {
        PaymentNotificationPreferences preference = new PaymentNotificationPreferences(payment.getCustomerDetails());

        final String templateName = NotificationConstants.paymentDisbursal;

        HashMap<WhatsappTemplateMessage, WhatsappStatus> whatsappMap = new HashMap<>();
        HashMap<BaseEmailTemplate, EmailStatus> emailMap = new HashMap<>();

        final Set<String> invoiceNumbers = payment.getInvoiceAmounts()
            .stream()
            .map(PaymentInvoiceAmount::getInvoiceNumber)
            .collect(Collectors.toSet());

        String collectionDateDetails = payment.getCollectionMetadata().getModificationDateTime().format(stdFormatter);
        String disbursalDate = payment.getDisbursalMetadata().getModificationDateTime().format(stdFormatter);

        if(!invoiceNumbers.isEmpty()){
            collectionDateDetails += " against the following invoices" + String.join(",", invoiceNumbers);
        }

        if(preference.isWhatsappEnabled()){
            WhatsappTemplateMessage message =
                new WhatsappTemplateMessage(payment.getSupplier().getPhoneNumber().toString(), templateName);

            WhatsappTemplateComponent bodyComponent =
                new WhatsappTemplateComponent(WhatsappTemplateComponent.Type.body, null, null);
            bodyComponent.getParameters()
                .add(new WhatsappTemplateParameterText(payment.getSupplier().getBusinessName()));
            bodyComponent.getParameters()
                .add(new WhatsappTemplateParameterText(String.valueOf(payment.getPaidAmount())));
            bodyComponent.getParameters()
                .add(new WhatsappTemplateParameterText(payment.getCustomerDetails().getBusinessName()));
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(collectionDateDetails));
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(disbursalDate));

            message.getTemplate().getComponents().add(bodyComponent);

            WhatsappStatus whatsappStatus =
                new WhatsappStatus(payment.getSupplier().getPhoneNumber(), templateName, payment.getCustomer());
            whatsappStatusRepository.save(whatsappStatus);
            whatsappMap.put(message, whatsappStatus);
        }

        if(preference.isEmailEnabled()){
            BaseEmailTemplate mail = new BaseEmailTemplate();
            mail.setTo(payment.getSupplier().getEmailId());
            mail.setTemplate(templateName);
            HashMap<String, String> templateValues = new HashMap<>();
            templateValues.put("supplier", payment.getSupplier().getBusinessName());
            templateValues.put("amount", String.valueOf(payment.getPaidAmount()));
            templateValues.put("retailer", payment.getCustomerDetails().getBusinessName());
            templateValues.put("dateDetail", collectionDateDetails);
            templateValues.put("disbursalDate", disbursalDate);
            mail.setMap(templateValues);

            EmailStatus emailStatus =
                new EmailStatus(payment.getSupplier().getEmailId(), templateName, payment.getCustomer());
            emailStatusRepository.save(emailStatus);
            emailMap.put(mail, emailStatus);
        }

        return new NotificationPayload(null, emailMap, whatsappMap);
    }

    public NotificationPayload buildPayloadForCreditConfirmation(@NonNull final Payment payment,
                                                                 final boolean isSupplierNotif) {

        PaymentCustomerDetails notifUserDetails =
            isSupplierNotif ? new PaymentCustomerDetails(payment.getSupplier()) : payment.getCustomerDetails();
        User notifSender = isSupplierNotif ? payment.getCustomer() : payment.getSupplier();
        PaymentNotificationPreferences preference = new PaymentNotificationPreferences(notifUserDetails);

        final String templateName = NotificationConstants.creditPaymentConfirmation;
        final String creditProvider = payment.getCollectionMetadata().getGateway().getName();
        final String user1 =
            isSupplierNotif ? payment.getSupplier().getBusinessName() : payment.getCustomerDetails().getBusinessName();
        final String user2 =
            isSupplierNotif ? "from " + payment.getCustomerDetails().getBusinessName() : "to " + payment.getSupplier()
                .getBusinessName();
        final Set<String> invoiceNumbers = payment.getInvoiceAmounts()
            .stream()
            .map(PaymentInvoiceAmount::getInvoiceNumber)
            .collect(Collectors.toSet());

        String collectionDateDetails = payment.getCollectionMetadata().getModificationDateTime().format(stdFormatter);

        if(!invoiceNumbers.isEmpty()){
            collectionDateDetails += " against the following invoices" + String.join(", ", invoiceNumbers);
        }

        HashMap<WhatsappTemplateMessage, WhatsappStatus> whatsappMap = new HashMap<>();
        HashMap<BaseEmailTemplate, EmailStatus> emailMap = new HashMap<>();

        if(preference.isWhatsappEnabled()){
            WhatsappTemplateMessage message =
                new WhatsappTemplateMessage(notifUserDetails.getPhoneNumber().toString(), templateName);

            WhatsappTemplateComponent bodyComponent =
                new WhatsappTemplateComponent(WhatsappTemplateComponent.Type.body, null, null);
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(user1));
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(creditProvider));
            bodyComponent.getParameters()
                .add(new WhatsappTemplateParameterText(String.valueOf(payment.getPaidAmount())));
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(user2));
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(collectionDateDetails));

            message.getTemplate().getComponents().add(bodyComponent);

            WhatsappStatus whatsappStatus =
                new WhatsappStatus(notifUserDetails.getPhoneNumber(), templateName, notifSender);
            whatsappStatusRepository.save(whatsappStatus);
            whatsappMap.put(message, whatsappStatus);
        }

        if(preference.isEmailEnabled()){
            BaseEmailTemplate mail = new BaseEmailTemplate();
            mail.setTo(notifUserDetails.getEmailId());
            mail.setTemplate(templateName);
            HashMap<String, String> templateValues = new HashMap<>();
            templateValues.put("user1", user1);
            templateValues.put("creditProvider", creditProvider);
            templateValues.put("amount", String.valueOf(payment.getPaidAmount()));
            templateValues.put("user2", user2);
            templateValues.put("collectionDate", collectionDateDetails);
            mail.setMap(templateValues);

            EmailStatus emailStatus =
                new EmailStatus(notifUserDetails.getEmailId(), templateName, notifSender);
            emailStatusRepository.save(emailStatus);
            emailMap.put(mail, emailStatus);
        }

        return new NotificationPayload(null, emailMap, whatsappMap);
    }

    public NotificationPayload buildPayloadForCreditDisbursal(@NonNull final Payment payment,
                                                              final boolean isSupplierNotif) {
        PaymentCustomerDetails notifUserDetails =
            isSupplierNotif ? new PaymentCustomerDetails(payment.getSupplier()) : payment.getCustomerDetails();
        User notifSender = isSupplierNotif ? payment.getCustomer() : payment.getSupplier();
        PaymentNotificationPreferences preference = new PaymentNotificationPreferences(notifUserDetails);

        final String templateName = NotificationConstants.creditPaymentDisbursal;
        final String creditProvider = payment.getDisbursalMetadata().getGateway().getName();
        final String user1 =
            isSupplierNotif ? payment.getSupplier().getBusinessName() : payment.getCustomerDetails().getBusinessName();
        final String user2 =
            isSupplierNotif ? "from " + payment.getCustomerDetails().getBusinessName() : "to " + payment.getSupplier()
                .getBusinessName();
        final String disbursalDate = payment.getDisbursalMetadata().getModificationDateTime().format(stdFormatter);
        final String utrNumber = payment.getDisbursalMetadata().getBankReferenceId();

        final Set<String> invoiceNumbers = payment.getInvoiceAmounts()
            .stream()
            .map(PaymentInvoiceAmount::getInvoiceNumber)
            .collect(Collectors.toSet());

        final String invoiceNumberStr = String.join(", ", invoiceNumbers);

        HashMap<WhatsappTemplateMessage, WhatsappStatus> whatsappMap = new HashMap<>();
        HashMap<BaseEmailTemplate, EmailStatus> emailMap = new HashMap<>();

        if(preference.isWhatsappEnabled()){
            WhatsappTemplateMessage message =
                new WhatsappTemplateMessage(notifUserDetails.getPhoneNumber().toString(), templateName);

            WhatsappTemplateComponent bodyComponent =
                new WhatsappTemplateComponent(WhatsappTemplateComponent.Type.body, null, null);
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(user1));
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(creditProvider));
            bodyComponent.getParameters()
                .add(new WhatsappTemplateParameterText(String.valueOf(payment.getPaidAmount())));
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(user2));
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(invoiceNumberStr));
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(disbursalDate));
            bodyComponent.getParameters().add(new WhatsappTemplateParameterText(utrNumber));

            message.getTemplate().getComponents().add(bodyComponent);

            WhatsappStatus whatsappStatus =
                new WhatsappStatus(notifUserDetails.getPhoneNumber(), templateName, notifSender);
            whatsappStatusRepository.save(whatsappStatus);
            whatsappMap.put(message, whatsappStatus);
        }

        if(preference.isEmailEnabled()){
            BaseEmailTemplate mail = new BaseEmailTemplate();
            mail.setTo(notifUserDetails.getEmailId());
            mail.setTemplate(templateName);
            HashMap<String, String> templateValues = new HashMap<>();
            templateValues.put("user1", user1);
            templateValues.put("creditProvider", creditProvider);
            templateValues.put("amount", String.valueOf(payment.getPaidAmount()));
            templateValues.put("user2", user2);
            templateValues.put("invoiceNumbers", invoiceNumberStr);
            templateValues.put("disbursalDate", disbursalDate);
            templateValues.put("utrNumber", utrNumber);
            mail.setMap(templateValues);

            EmailStatus emailStatus =
                new EmailStatus(notifUserDetails.getEmailId(), templateName, notifSender);
            emailStatusRepository.save(emailStatus);
            emailMap.put(mail, emailStatus);
        }

        return new NotificationPayload(null, emailMap, whatsappMap);
    }

    @Data
    private static class PaymentNotificationPreferences {
        private boolean whatsappEnabled = false;
        private boolean emailEnabled = false;

        PaymentNotificationPreferences(PaymentCustomerDetails paymentCustomer) {
            if(paymentCustomer.getPhoneNumber() != null){
                this.whatsappEnabled = true;
            }
            if(StringUtils.isNotBlank(paymentCustomer.getEmailId())){
                this.emailEnabled = true;
            }
        }
    }
}
