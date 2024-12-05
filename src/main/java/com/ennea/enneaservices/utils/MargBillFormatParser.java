package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.constants.Constants;
import com.ennea.enneaservices.model.Invoice;
import com.ennea.enneaservices.model.InvoiceDetail;
import com.ennea.enneaservices.model.Ledger;
import com.ennea.enneaservices.model.Order;
import com.ennea.enneaservices.model.SupplierCustomer;
import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.repository.LedgerRepository;
import com.ennea.enneaservices.repository.SupplierCustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ennea.enneaservices.utils.DateTimeUtils.parseDate;

@Component
public class MargBillFormatParser {

    private final Logger logger = LoggerFactory.getLogger(MargBillFormatParser.class);

    private final SupplierCustomerRepository supplierCustomerRepository;

    private final LedgerRepository ledgerRepository;

    @Autowired
    public MargBillFormatParser(SupplierCustomerRepository supplierCustomerRepository,
                                LedgerRepository ledgerRepository) {
        this.supplierCustomerRepository = supplierCustomerRepository;
        this.ledgerRepository = ledgerRepository;
    }

    public Invoice processBillFormat(String bill, final User supplier, Order invoiceOrder) {
        try {
            bill = bill.replace("\n", "");
            final String[] headerAndTransactions = bill.split(Constants.MARG_BILL_FORMAT_FOOTER_SPLITTER);
            final String header = headerAndTransactions[0].split(Constants.MARG_BILL_FORMAT_HEADER_SPLITTER)[0];

            final String[] headerColumns = header.split(",");
            final LocalDateTime creationDateTime = DateTimeUtils.dateTimeInIST().toLocalDateTime();
            Invoice invoice = new Invoice();
            if(StringUtils.isBlank(headerColumns[29]) && invoiceOrder == null){
                logger.error("Marg Invoice not getting created empty alias and order not found");
                return null;
            }
            logger.info("printing the party code: {}", headerColumns[29]);
            invoice.setSupplier(supplier);
            if(StringUtils.isBlank(headerColumns[29])){
                invoice.setCustomer(invoiceOrder.getCustomer());
                invoice.setPartyCode(invoiceOrder.getCustomerDetails().getPartyCode());
                invoice.setPartyName(invoiceOrder.getCustomerDetails().getBusinessName());
            } else{
                Optional<Ledger>
                    ledgerOptional = ledgerRepository.findBySupplierAndAlias(supplier, headerColumns[29]);

                if(ledgerOptional.isPresent()){
                    Optional<SupplierCustomer> supplierCustomerOptional =
                        supplierCustomerRepository.findBySupplierAndCustomerPartyCode(supplier,
                                                                                      ledgerOptional.get()
                                                                                          .getPartyCode());
                    if(supplierCustomerOptional.isPresent()){
                        invoice.setCustomer(supplierCustomerOptional.get().getCustomer());
                        invoice.setPartyName(supplierCustomerOptional.get().getCustomerPartyName());
                        if(StringUtils.isNotBlank(supplierCustomerOptional.get().getCustomerPartyCode())){
                            invoice.setPartyCode(supplierCustomerOptional.get().getCustomerPartyCode());
                        } else{
                            invoice.setPartyCode(headerColumns[29]);
                        }
                    } else{
                        invoice.setPartyName(headerColumns[30]);
                        invoice.setPartyCode(headerColumns[29]);
                    }
                } else{
                    invoice.setPartyName(headerColumns[30]);
                    invoice.setPartyCode(headerColumns[29]);
                }
            }

            invoice.setInvoiceNumber(headerColumns[2]);
            invoice.setInvoiceDate(parseDate(headerColumns[3], "ddMMyyyy"));
            invoice.setCreationDateTime(creationDateTime);
            invoice.setModificationDateTime(creationDateTime);

            final String[] splitTransactions =
                headerAndTransactions[0].split(Constants.MARG_BILL_FORMAT_TRANSACTION_SPLITTER);
            List<InvoiceDetail> invoiceDetails = new ArrayList<>();
            for(int i = 1; i < splitTransactions.length; i++){
                InvoiceDetail detail = new InvoiceDetail();
                final String[] transactionColumns = splitTransactions[i].split(",");
                Double actualRate = Double.parseDouble(transactionColumns[13]);
                Float quantity = Float.parseFloat(transactionColumns[19]);
                Double value = actualRate * quantity;
                detail.setProductName(transactionColumns[4]);
                detail.setProductCode(transactionColumns[36]);
                detail.setPacking(transactionColumns[5]);
                detail.setDivision(transactionColumns[1]);
                detail.setBatchNumber(transactionColumns[7]);
                detail.setExpiryDate(parseDate(transactionColumns[8], "ddMMyyyy"));
                detail.setProductRate(Double.parseDouble(transactionColumns[12]));
                detail.setActualRate(actualRate);
                detail.setMrp(Double.parseDouble(transactionColumns[15]));
                detail.setQuantity(quantity);
                detail.setFree(Float.parseFloat(transactionColumns[20]));
                detail.setDiscount(Double.parseDouble(transactionColumns[22]));
                detail.setTax(Double.parseDouble(transactionColumns[25]));
                detail.setValue(value);
                invoiceDetails.add(detail);
            }
            invoice.setGrossValue(invoiceDetails.stream().mapToDouble(InvoiceDetail::getValue).sum());
            invoice.setTax(invoiceDetails.stream().mapToDouble(InvoiceDetail::getTax).sum());
            invoice.setDiscount(invoiceDetails.stream().mapToDouble(InvoiceDetail::getDiscount).sum());
            invoice.setNetValue(invoice.getGrossValue() - invoice.getDiscount() + invoice.getTax());
            invoice.setPendingAmount(invoice.getGrossValue() - invoice.getDiscount() + invoice.getTax());
            logger.info("invoice code : {}", invoice.getPartyCode());
            if(invoiceDetails.isEmpty()){
                logger.warn("Skipping empty invoice : {}", invoice.getInvoiceNumber());
                return null;
            }
            invoice.setDetails(invoiceDetails);
            logger.info("Parsed invoice : {} successfully", invoice.getInvoiceNumber());
            return invoice;
        } catch (Exception e) {
            logger.error("Error parsing invoice record, {}", e.getMessage());
            return null;
        }
    }

}
