package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.model.Invoice;

public class InvoiceSummaryUtils {

    public static String getInvoiceSummary(Invoice invoice) {
        return "Click the link to view invoice details and update delivery status." + " <br>" +
               "https://www.valuemedi.com/updateInvoice/"
               + invoice.getCustomer().getId()
               + "/"
               + invoice.getUpdateId();
    }
}
