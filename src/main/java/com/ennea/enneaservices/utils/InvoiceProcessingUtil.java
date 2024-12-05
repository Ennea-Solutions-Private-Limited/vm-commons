package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.constants.Constants;
import com.ennea.enneaservices.model.Dto.Marg.MargLiveOrderStatusOrderInfo;
import com.ennea.enneaservices.model.Dto.Marg.MargLiveOrderStatusOrderMain;
import com.ennea.enneaservices.model.Dto.Marg.MargLiveOrderStatusResponse;
import com.ennea.enneaservices.model.ErpCredentials;
import com.ennea.enneaservices.model.Invoice;
import com.ennea.enneaservices.model.Ledger;
import com.ennea.enneaservices.model.Order;
import com.ennea.enneaservices.model.OrderInvoice;
import com.ennea.enneaservices.model.OrderStatus;
import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.repository.InvoiceRepository;
import com.ennea.enneaservices.repository.LedgerRepository;
import com.ennea.enneaservices.repository.OrderInvoiceRepository;
import com.ennea.enneaservices.repository.OrderStatusRepository;
import com.ennea.enneaservices.repository.OrdersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class InvoiceProcessingUtil {

    private final OrderInvoiceRepository orderInvoiceRepository;

    private final OrdersRepository ordersRepository;

    private final OrderStatusRepository orderStatusRepository;

    private final MargBillFormatParser margBillFormatParser;

    private final InvoiceRepository invoiceRepository;

    private final LedgerRepository ledgerRepository;

    @Autowired
    public InvoiceProcessingUtil(OrderInvoiceRepository orderInvoiceRepository, OrdersRepository ordersRepository,
                                 OrderStatusRepository orderStatusRepository, MargBillFormatParser margBillFormatParser,
                                 InvoiceRepository invoiceRepository, LedgerRepository ledgerRepository) {
        this.orderInvoiceRepository = orderInvoiceRepository;
        this.ordersRepository = ordersRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.margBillFormatParser = margBillFormatParser;
        this.invoiceRepository = invoiceRepository;
        this.ledgerRepository = ledgerRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void margOrderInvoiceTransaction(List<OrderInvoice> orderInvoices) {
        if(orderInvoices != null && !orderInvoices.isEmpty()){
            orderInvoiceRepository.saveAll(orderInvoices);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<OrderInvoice> getNewOrderInvoices(String decompressedResponse, User distributor)
        throws JSONException {

        ObjectMapper mapper = new ObjectMapper();
        MargLiveOrderStatusResponse margLiveOrderStatusResponse;
        try {
            margLiveOrderStatusResponse = mapper.readValue(decompressedResponse, MargLiveOrderStatusResponse.class);
        } catch (JsonProcessingException e) {
            log.info("Marg parse response error for user:{} response: {}", distributor.getId(), decompressedResponse);
            log.error("Error for above user: {} error: {}", distributor.getId(), e.getMessage());
            return new ArrayList<>();
        }
        List<MargLiveOrderStatusOrderMain> orderMains = margLiveOrderStatusResponse.getDetails().getOrderMain();
        List<MargLiveOrderStatusOrderInfo> orderInfos = margLiveOrderStatusResponse.getDetails().getOrderInfo();

        List<Order> margProcessingOrders =
            ordersRepository.findAllOrdersBySupplierAndOrderStatus(distributor.getId(),
                                                                   Constants.ORDER_PROCESSING);

        Set<String> uniqueOrderNumbers = orderInfos.stream().map(
            MargLiveOrderStatusOrderInfo::getOrderId).filter(
            StringUtils::isNotBlank).collect(
            Collectors.toSet());
        for(var order : margProcessingOrders){
            if(StringUtils.isNotBlank(order.getReferenceId())){
                uniqueOrderNumbers.add(order.getReferenceId());
            }
        }

        String dateTime = margLiveOrderStatusResponse.getDetails().getDateTime();

        List<String> orderNumbers = new ArrayList<>();
        List<OrderInvoice> orderInvoices = new ArrayList<>();
        for(var orderNumber : uniqueOrderNumbers){
            MargLiveOrderStatusOrderMain validOrderMain = null;
            for(var orderMain : orderMains){
                if(StringUtils.isNotBlank(orderMain.getOrderNo()) && orderMain.getOrderNo().contains(orderNumber)){
                    validOrderMain = orderMain;
                    break;
                }
            }
            if(validOrderMain != null){
                String billFormat = validOrderMain.getBillFmt();
                String salesMemo = validOrderMain.getSalesMemo();
                orderNumbers.add(orderNumber);
                OrderInvoice orderInvoice = new OrderInvoice();
                orderInvoice.setDistributorId(distributor.getId());
                orderInvoice.setOrderNumber(orderNumber);
                orderInvoice.setInvoiceOrderStatus(Constants.MARG_INVOICE_ORDER_STATUS_IN_TRANSIT);
                orderInvoice.setInvoiceDateCreated(DateTimeUtils.dateTimeInIST().toLocalDate());
                orderInvoice.setInvoiceDateUpdated(DateTimeUtils.dateTimeInIST().toLocalDate());
                orderInvoice.setDateTime(dateTime);
                orderInvoice.setInvoiceResponse(billFormat + " && " + salesMemo);
                orderInvoices.add(orderInvoice);
                orderInvoice.setOrderMainNumber(validOrderMain.getOrderNo());
            }
        }
        /*
         * The invoices returned will be processed multiple times in the following cases
         * Deletion of job execution, Method called with salesman null etc
         * That is why the below code exists
         * */
        Set<String> allExistingOrderNumbers =
            orderInvoiceRepository.findExistingOrderNumbers(distributor.getId(), orderNumbers);

        // Add all the order invoices which are not present in our table with in-transit status
        return orderInvoices.stream()
            .filter(orderInvoice -> !allExistingOrderNumbers.contains(orderInvoice.getOrderNumber()))
            .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void margInvoicesBackfillPending(ErpCredentials erpCredentials) {
        /*
         * This cron for updating the invoices pending amount based on the outstanding amount from ledgers
         * */
        List<Ledger> ledgers = ledgerRepository.findAllBySupplier(erpCredentials.getUser());
        Map<String, Double> partyCodeToOutstanding = new HashMap<>();
        ledgers.forEach(ledger -> partyCodeToOutstanding.put(ledger.getPartyCode(), ledger.getBalance()));
        LocalDate invoicesFromDate = LocalDate.now().minusMonths(2);
        List<Invoice> invoices =
            invoiceRepository.findAllBySupplierAndPaidStatusAndInvoiceDate(erpCredentials.getUser(), false,
                                                                           invoicesFromDate);
        Map<String, List<Invoice>> partyCodeToInvoicesMap =
            invoices.stream().collect(Collectors.groupingBy(Invoice::getPartyCode));

        partyCodeToInvoicesMap.keySet().forEach(partyCode -> {
            if(StringUtils.isBlank(partyCode)){
                return;
            }
            log.info("Marg invoice processing started for party: {}, supplier: {}", partyCode,
                     erpCredentials.getUser().getId());
            List<Invoice> partyInvoices = partyCodeToInvoicesMap.get(partyCode);
            Double possibleOutstanding = partyCodeToOutstanding.get(partyCode);
            if(possibleOutstanding == null || possibleOutstanding.equals(0.0)){
                partyInvoices.forEach(invoice -> {
                    invoice.setPaid(true);
                    invoice.setPendingAmount(0.0);
                });
                return;
            }
            double outstanding = possibleOutstanding;
            for(Invoice invoice : partyInvoices){
                if(outstanding <= 0.0){
                    invoice.setPendingAmount(0.0);
                    invoice.setPaid(true);
                    continue;
                }
                double invoicePendingAmount = invoice.getNetValue();
                if(outstanding >= invoicePendingAmount){
                    outstanding = outstanding - invoicePendingAmount;
                    invoice.setPendingAmount(invoicePendingAmount);
                } else{
                    invoice.setPendingAmount(outstanding);
                    outstanding = 0.0;
                }
            }
        });
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void parseDistributorInvoices(User supplier) {
        List<OrderStatus> allOrderStatuses = (List<OrderStatus>) orderStatusRepository.findAll();
        Map<String, Integer> orderStatusToStatusId = allOrderStatuses.stream()
            .collect(Collectors.toMap(OrderStatus::getStatus, OrderStatus::getId));
        OrderStatus cancelledStatus = allOrderStatuses.stream().filter(
            orderStatus -> orderStatus.getStatus().equalsIgnoreCase(Constants.ORDER_CANCELLED)).toList().get(0);
        final List<OrderInvoice> orderInvoices = orderInvoiceRepository
            .findAllByProcessedAndInvoiceOrderStatusAndSupplier(false,
                                                                Constants.MARG_INVOICE_ORDER_STATUS_IN_TRANSIT,
                                                                supplier.getId());
        log.info("Parsing {} marg order invoices from cron for user {}", orderInvoices.size(), supplier.getId());
        final List<String> referenceNumbers =
            orderInvoices.stream().map(OrderInvoice::getOrderNumber).collect(Collectors.toList());
        final List<Order> orders = ordersRepository.findBySupplierAndOrderStatusAndOrderNumbers(supplier.getId(),
                                                                                                Constants.ORDER_PROCESSING,
                                                                                                referenceNumbers);

        Map<String, Order> referenceIdToOrder = new HashMap<>();
        orders.forEach(order -> referenceIdToOrder.put(order.getReferenceId(), order));

        Map<String, List<OrderInvoice>> orderMainNumberToOrderInvoices = orderInvoices.stream().filter(
            orderInvoice -> StringUtils.isNotBlank(orderInvoice.getOrderMainNumber())).collect(
            Collectors.groupingBy(OrderInvoice::getOrderMainNumber, Collectors.toList()));

        Map<String, List<Order>> orderMainNumberToOrders = new HashMap<>();

        orderMainNumberToOrderInvoices.forEach((key, value) -> {
            List<Order> tempOrders = value.stream().map(OrderInvoice::getOrderNumber).map(
                referenceIdToOrder::get).filter(Objects::nonNull).collect(
                Collectors.toList());
            orderMainNumberToOrders.put(key, tempOrders);
        });

        Map<String, Invoice> orderMainToInvoice = new HashMap<>();

        Map<String, Invoice> newInvoicesMap = new HashMap<>();
        List<Long> toBeUpdatedOrderIds = new ArrayList<>();
        orderInvoices.forEach(orderInvoice -> {
            if(StringUtils.isBlank(orderInvoice.getInvoiceResponse())
               || StringUtils.isBlank(orderInvoice.getOrderNumber())){
                log.info("Invoice response or invoice order is blank for : {}", orderInvoice.getId());
                orderInvoice.setProcessed(true);
                return;
            }
            log.info("Parsing invoice corresponding to reference id : {}", orderInvoice.getOrderNumber());
            Order invoiceOrder = referenceIdToOrder.get(orderInvoice.getOrderNumber());
            final Invoice invoice =
                margBillFormatParser.processBillFormat(orderInvoice.getInvoiceResponse(), supplier, invoiceOrder);

            if(invoice != null){
                Order order = referenceIdToOrder.get(orderInvoice.getOrderNumber());
                if(order != null && invoice.getPartyCode().equalsIgnoreCase(
                    order.getCustomerDetails().getPartyCode())){
                    toBeUpdatedOrderIds.add(order.getId());
                }
                invoice.setSalesOrderId(orderInvoice.getOrderNumber());
                newInvoicesMap.put(invoice.getInvoiceNumber(), invoice);
                orderMainToInvoice.put(orderInvoice.getOrderMainNumber(), invoice);
            }
            orderInvoice.setProcessed(true);
        });

        orderMainToInvoice.forEach((orderMainNumber, invoice) -> {
            List<Order> groupedOrders = orderMainNumberToOrders.get(orderMainNumber);
            if(groupedOrders == null || groupedOrders.isEmpty()){
                return;
            }
            groupedOrders.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
            Map<String, Float> productCodeToQuantity = new HashMap<>();
            Map<String, Float> productCodeToFreeQuantity = new HashMap<>();

            invoice.getDetails().forEach(invoiceDetail -> {
                if(StringUtils.isBlank(invoiceDetail.getProductCode())){
                    return;
                }
                String formattedProductCode = invoiceDetail.getProductCode().replaceAll("[^0-9]", "");
                if(StringUtils.isBlank(formattedProductCode)){
                    return;
                }
                if(productCodeToQuantity.containsKey(formattedProductCode)){
                    productCodeToQuantity.put(formattedProductCode,
                                              invoiceDetail.getQuantity() + productCodeToQuantity.get(
                                                  formattedProductCode));
                } else{
                    productCodeToQuantity.put(formattedProductCode, invoiceDetail.getQuantity());
                }

                if(productCodeToFreeQuantity.containsKey(formattedProductCode)){
                    productCodeToFreeQuantity.put(formattedProductCode,
                                                  invoiceDetail.getFree() + productCodeToFreeQuantity.get(
                                                      formattedProductCode));
                } else{
                    productCodeToFreeQuantity.put(formattedProductCode, invoiceDetail.getFree());
                }
            });

            groupedOrders.forEach(order -> order.getDetails().forEach(details -> {
                if(productCodeToQuantity.containsKey(details.getProductCode())){
                    if(details.getOrderedQuantity() > productCodeToQuantity.get(details.getProductCode())){
                        details.setBilledOrderedQuantity(productCodeToQuantity.get(details.getProductCode()));
                        productCodeToQuantity.remove(details.getProductCode());
                    } else if(details.getOrderedQuantity() < productCodeToQuantity.get(details.getProductCode())){
                        details.setBilledOrderedQuantity(details.getOrderedQuantity());
                        productCodeToQuantity.put(details.getProductCode(), productCodeToQuantity.get(
                            details.getProductCode()) - details.getBilledOrderedQuantity());
                    } else{
                        details.setBilledOrderedQuantity(details.getOrderedQuantity());
                        productCodeToQuantity.remove(details.getProductCode());
                    }
                }

                if(productCodeToFreeQuantity.containsKey(details.getProductCode())){
                    if(details.getFreeQuantity() > productCodeToFreeQuantity.get(details.getProductCode())){
                        details.setBilledFreeQuantity(productCodeToFreeQuantity.get(details.getProductCode()));
                        productCodeToFreeQuantity.remove(details.getProductCode());
                    } else if(details.getFreeQuantity() < productCodeToFreeQuantity.get(
                        details.getProductCode())){
                        details.setBilledFreeQuantity(details.getFreeQuantity());
                        productCodeToFreeQuantity.put(details.getProductCode(), productCodeToFreeQuantity.get(
                            details.getProductCode()) - details.getBilledFreeQuantity());
                    } else{
                        details.setBilledFreeQuantity(details.getFreeQuantity());
                        productCodeToFreeQuantity.remove(details.getProductCode());
                    }
                }

            }));
            groupedOrders.sort((o1, o2) -> (int) (o2.getId() - o1.getId()));
            groupedOrders.forEach(order -> order.getDetails().forEach(details -> {
                if(productCodeToQuantity.containsKey(details.getProductCode())){
                    details.setBilledOrderedQuantity(
                        details.getBilledOrderedQuantity() + productCodeToQuantity.get(
                            details.getProductCode()));

                    productCodeToQuantity.remove(details.getProductCode());
                }

                if(productCodeToFreeQuantity.containsKey(details.getProductCode())){
                    details.setBilledFreeQuantity(
                        details.getBilledFreeQuantity() + productCodeToFreeQuantity.get(
                            details.getProductCode()));
                    productCodeToQuantity.remove(details.getProductCode());
                }
            }));
            groupedOrders.forEach(order -> {
                boolean allBilledIsZero =
                    order.getDetails().stream().allMatch(details -> details.getBilledOrderedQuantity() == 0);
                if(allBilledIsZero){
                    order.setOrderStatus(cancelledStatus);
                }
            });

        });

        List<Invoice> existingInvoices =
            invoiceRepository.findByInvoiceNumbersAndSupplier(newInvoicesMap.keySet(), supplier);
        Map<String, List<Invoice>> existingInvoicesMap = existingInvoices.stream()
            .collect(Collectors.groupingBy(Invoice::getInvoiceNumber, Collectors.toList()));
        Map<String, Invoice> finalNewInvoicesMap = new HashMap<>();
        newInvoicesMap.values().forEach(invoice -> {
            List<Invoice> existingInvoicesByInvoiceNumber = existingInvoicesMap.get(invoice.getInvoiceNumber());
            if(existingInvoicesByInvoiceNumber != null){
                Optional<Invoice> sameExistingInvoice = existingInvoicesByInvoiceNumber.stream()
                    .filter(invoice1 -> invoice1.getPartyCode().equals(invoice.getPartyCode())
                                        && invoice1.getInvoiceDate().isEqual(invoice.getInvoiceDate())).findFirst();
                if(sameExistingInvoice.isPresent()){
                    updateExistingInvoice(sameExistingInvoice.get(), invoice);
                } else{
                    finalNewInvoicesMap.put(invoice.getInvoiceNumber(), invoice);
                }
            } else{
                finalNewInvoicesMap.put(invoice.getInvoiceNumber(), invoice);
            }
        });
        ordersRepository.updateOrderStatusByOrderIds(supplier.getId(),
                                                     toBeUpdatedOrderIds,
                                                     orderStatusToStatusId.get(Constants.ORDER_INVOICED),
                                                     orderStatusToStatusId.get(Constants.ORDER_PROCESSING),
                                                     DateTimeUtils.dateTimeInIST().toLocalDateTime());
        log.info("Saving {} new invoices for user: {}", finalNewInvoicesMap.size(), supplier.getId());
        invoiceRepository.saveAll(new ArrayList<>(finalNewInvoicesMap.values()));
    }

    public void updateExistingInvoice(Invoice existingInvoice, Invoice updatedInvoice) {
        existingInvoice.setModificationDateTime(DateTimeUtils.dateTimeInIST().toLocalDateTime());
        existingInvoice.setGrossValue(updatedInvoice.getGrossValue());
        existingInvoice.setTax(updatedInvoice.getTax());
        existingInvoice.setDiscount(updatedInvoice.getDiscount());
        existingInvoice.setNetValue(updatedInvoice.getGrossValue() - updatedInvoice.getDiscount()
                                    + updatedInvoice.getTax());
        existingInvoice.setDetails(updatedInvoice.getDetails());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateOldMargInvoicesToPaid(List<ErpCredentials> erpCredentials) {
        LocalDate beforeTwoMonths = LocalDate.now().minusMonths(2);
        invoiceRepository.updateInvoiceToPaidByDateAndSuppliers(
            erpCredentials.stream().map(ErpCredentials::getUser).toList(), beforeTwoMonths);
    }
}

