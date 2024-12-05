package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.constants.Constants;
import com.ennea.enneaservices.model.Details;
import com.ennea.enneaservices.model.Dto.Marg.MargInventoryProduct;
import com.ennea.enneaservices.model.Dto.Marg.MargTransactionUpdate;
import com.ennea.enneaservices.model.Dto.ProfitMaker.ProfitMakerInventoryProduct;
import com.ennea.enneaservices.model.Dto.ProfitMaker.ProfitMakerInventoryProductV2;
import com.ennea.enneaservices.model.InventoryAvailability;
import com.ennea.enneaservices.model.JobExecutionReport;
import com.ennea.enneaservices.model.Order;
import com.ennea.enneaservices.model.Scheme;
import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.repository.InventoryAvailabilityRepository;
import com.ennea.enneaservices.repository.JobExecutionReportRepository;
import com.ennea.enneaservices.repository.OrdersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InventoryProcessingUtil {

    private final Logger logger = LoggerFactory.getLogger(InventoryProcessingUtil.class);

    private final InventoryAvailabilityRepository inventoryAvailabilityRepository;

    private final EmailUtils emailUtils;

    private final JobExecutionReportRepository jobExecutionReportRepository;

    private final OrdersRepository ordersRepository;

    @Autowired
    public InventoryProcessingUtil(InventoryAvailabilityRepository inventoryAvailabilityRepository,
                                   EmailUtils emailUtils, JobExecutionReportRepository jobExecutionReportRepository,
                                   OrdersRepository ordersRepository) {
        this.inventoryAvailabilityRepository = inventoryAvailabilityRepository;
        this.emailUtils = emailUtils;
        this.jobExecutionReportRepository = jobExecutionReportRepository;
        this.ordersRepository = ordersRepository;
    }

    private static void updateAvailability(Map<String, Integer> productCodeToCount,
                                           Map<String, InventoryAvailability> existingInventoryMap,
                                           Map<String, InventoryAvailability> updatedInventoryMap,
                                           MargInventoryProduct product) {
        if(existingInventoryMap.containsKey(product.getCode())){
            InventoryAvailability inventoryAvailability = existingInventoryMap.get(product.getCode());
            if(productCodeToCount.containsKey(product.getCode())){
                int newStock = (int) Double.parseDouble(product.getStock());
                int existingStock = productCodeToCount.get(product.getCode());
                inventoryAvailability.setAvailability(newStock - existingStock);
            } else{
                int newStock = (int) Double.parseDouble(product.getStock());
                inventoryAvailability.setAvailability(newStock);
            }
            inventoryAvailability.setModificationDateTime(DateTimeUtils.dateTimeInIST().toLocalDateTime());
            updatedInventoryMap.put(inventoryAvailability.getProductCode(), inventoryAvailability);
        }
    }

    private static void updateRateAndScheme(Map<String, InventoryAvailability> existingInventoryMap,
                                            Map<String, InventoryAvailability> updatedInventoryMap,
                                            MargInventoryProduct product) {
        if(existingInventoryMap.containsKey(product.getCode())){
            InventoryAvailability inventoryAvailability = existingInventoryMap.get(product.getCode());
            inventoryAvailability.setMrp(Double.parseDouble(product.getMRP()));
            inventoryAvailability.setPtr(Double.parseDouble(product.getRate()));

            if(Integer.parseInt(product.getDeal()) > 0){
                final Scheme scheme = new Scheme();
                scheme.setDeal(Integer.parseInt(product.getDeal()));
                scheme.setFree(Integer.parseInt(product.getFree()));
                inventoryAvailability.setSchemes(new ArrayList<>(List.of(scheme)));
                inventoryAvailability.setDeal(Integer.parseInt(product.getDeal()));
                inventoryAvailability.setFree(Integer.parseInt(product.getFree()));
            } else{
                inventoryAvailability.setSchemes(new ArrayList<>());
                inventoryAvailability.setDeal(0);
                inventoryAvailability.setFree(0);
            }

            inventoryAvailability.setModificationDateTime(DateTimeUtils.dateTimeInIST().toLocalDateTime());
            updatedInventoryMap.put(inventoryAvailability.getProductCode(), inventoryAvailability);
        }
    }

    public static String getSearchPrefixes(String productName) {
        List<String> finalPrefixes = new ArrayList<>();
        String withSpaces = productName.replaceAll("[^a-zA-Z0-9]", " ");
        String afterRemovingExtraSpaces = withSpaces.trim().replaceAll(" +", " ");
        String[] token = afterRemovingExtraSpaces.split(" ");
        String previousToken = "";
        for(String string : token){
            if(string.length() >= 3 && previousToken.isEmpty()){
                finalPrefixes.add(string.substring(0, 3));
            } else if(string.length() >= 3 && !previousToken.isEmpty()){
                finalPrefixes.add(string.substring(0, 3));
                String addedString = previousToken + string.substring(0, 3 - previousToken.length());
                finalPrefixes.add(addedString);
                previousToken = "";
            } else if(string.length() < 3 && previousToken.isEmpty()){
                previousToken = string;
            } else if(string.length() < 3 && !previousToken.isEmpty()){
                String addedTokens = previousToken + string;
                if(addedTokens.length() >= 3){
                    finalPrefixes.add(addedTokens.substring(0, 3));
                    previousToken = "";
                } else{
                    previousToken = addedTokens;
                }
            }
        }
        return finalPrefixes.stream().limit(3).reduce("", (s, s2) -> s + " " + s2);
    }

    private void addProductsToList(JSONArray productsArray, List<MargInventoryProduct> inventoryList) {
        for(int i = 0; i < productsArray.length(); i++){
            JSONObject productObj = productsArray.getJSONObject(i);
            MargInventoryProduct margInventoryProduct = prepareMargInventoryProduct(productObj);
            if(margInventoryProduct != null){
                inventoryList.add(margInventoryProduct);
            }
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public MargTransactionUpdate getInventoryUpdate(String decompressedResponse, User user) {


        List<String> receivedAndInvoicing = new ArrayList<>();
        receivedAndInvoicing.add(Constants.ORDER_RECEIVED);
        receivedAndInvoicing.add(Constants.ORDER_PROCESSING);

        List<Order> orders = ordersRepository.findAllSupplierOrdersByStatuses(user, receivedAndInvoicing);

        Map<String, Integer> productCodeToCount = orders.stream().flatMap(order -> order.getDetails().stream()).collect(
            Collectors.groupingBy(Details::getProductCode, Collectors.summingInt(value -> (int) (value.getOrderedQuantity() + value.getFreeQuantity()))));

        if(StringUtils.isBlank(decompressedResponse)){
            logger.error("Decompressed response null for user: {}", user.getId());
            JobExecutionReport jobExecutionReport =
                JobExecutionReportUtils.prepareJobExecutionReport(user, Constants.JOB_TYPE_INVENTORY,
                                                                  Constants.JOB_STATUS_FAILED,
                                                                  DateTimeUtils.dateTimeInIST().toLocalDateTime(),
                                                                  Constants.NO_RECORDS_MODIFIED, "Response null");

            MargTransactionUpdate margTransactionUpdate = new MargTransactionUpdate();
            margTransactionUpdate.setJobExecutionReport(jobExecutionReport);
            return margTransactionUpdate;
        }

        int modifiedRecords;
        List<MargInventoryProduct> newInventoryList = new ArrayList<>();
        List<MargInventoryProduct> deltaInventoryList = new ArrayList<>();
        List<MargInventoryProduct> rateUpdateInventoryList = new ArrayList<>();
        List<MargInventoryProduct> stockUpdateInventoryList = new ArrayList<>();
        try {
            JSONObject responseObj = new JSONObject(decompressedResponse);
            JSONObject details = responseObj.getJSONObject("Details");
            JSONArray newProducts = details.getJSONArray("pro_N");
            JSONArray deltaProducts = details.getJSONArray("pro_U");
            JSONArray changeInRateProducts = details.getJSONArray("pro_R");
            JSONArray stockUpdates = details.getJSONArray("pro_S");

            addProductsToList(newProducts, newInventoryList);
            addProductsToList(deltaProducts, deltaInventoryList);
            addProductsToList(changeInRateProducts, rateUpdateInventoryList);
            addProductsToList(stockUpdates, stockUpdateInventoryList);
        } catch (JSONException e) {
            logger.error("Unable to read inventory response for distributor : {}", user.getId(), e);
            final String message = JobExecutionReportUtils.prepareFailureStackTrace(e);
            JobExecutionReport jobExecutionReport =
                JobExecutionReportUtils.prepareJobExecutionReport(user, Constants.JOB_TYPE_INVENTORY,
                                                                  Constants.JOB_STATUS_FAILED,
                                                                  DateTimeUtils.dateTimeInIST().toLocalDateTime(),
                                                                  Constants.NO_RECORDS_MODIFIED, message);
            MargTransactionUpdate margTransactionUpdate = new MargTransactionUpdate();
            margTransactionUpdate.setJobExecutionReport(jobExecutionReport);
            return margTransactionUpdate;
        }
        MargTransactionUpdate margTransactionUpdate = new MargTransactionUpdate();
        try {
            modifiedRecords =
                newInventoryList.size()
                + deltaInventoryList.size()
                + rateUpdateInventoryList.size()
                + stockUpdateInventoryList.size();
            List<InventoryAvailability> updated =
                processInventory(newInventoryList, deltaInventoryList, rateUpdateInventoryList,
                                 stockUpdateInventoryList,
                                 user, productCodeToCount);
            margTransactionUpdate.setInventoryAvailabilityList(updated);
        } catch (Exception e) {
            logger.error("Unable to process inventory for distributor : {}", user.getId(), e);
            final String message = JobExecutionReportUtils.prepareFailureStackTrace(e);
            JobExecutionReport jobExecutionReport =
                JobExecutionReportUtils.prepareJobExecutionReport(user, Constants.JOB_TYPE_INVENTORY,
                                                                  Constants.JOB_STATUS_FAILED,
                                                                  DateTimeUtils.dateTimeInIST().toLocalDateTime(),
                                                                  Constants.NO_RECORDS_MODIFIED, message);
            margTransactionUpdate.setJobExecutionReport(jobExecutionReport);
            return margTransactionUpdate;
        }
        JobExecutionReport jobExecutionReport =
            JobExecutionReportUtils.prepareJobExecutionReport(user, Constants.JOB_TYPE_INVENTORY,
                                                              Constants.JOB_STATUS_SUCCESS,
                                                              DateTimeUtils.dateTimeInIST().toLocalDateTime(),
                                                              modifiedRecords, null);

        margTransactionUpdate.setJobExecutionReport(jobExecutionReport);
        return margTransactionUpdate;
    }

    private MargInventoryProduct prepareMargInventoryProduct(JSONObject obj) throws JSONException {
        MargInventoryProduct margInventoryProduct;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            margInventoryProduct = objectMapper.readValue(obj.toString(), MargInventoryProduct.class);
            return margInventoryProduct;
        } catch (Exception e) {
            logger.error("Error while converting json to marg inventory product, {}", e.toString());
            return null;
        }
    }

    private List<InventoryAvailability> processInventory(List<MargInventoryProduct> newInventoryList,
                                                         List<MargInventoryProduct> deltaInventoryList,
                                                         List<MargInventoryProduct> rateUpdates,
                                                         List<MargInventoryProduct> stockUpdates,
                                                         User distributor,
                                                         Map<String, Integer> productCodeToCount) {
        Map<String, InventoryAvailability> existingInventoryMap = new HashMap<>();
        Map<String, InventoryAvailability> updatedInventoryMap = new HashMap<>();
        Map<String, String> deletedProducts = new HashMap<>();

        existingInventory(existingInventoryMap, distributor);
        newInventoryList.forEach(product -> updateStatus(distributor, productCodeToCount, existingInventoryMap, updatedInventoryMap, deletedProducts,
                                                     product));

        deltaInventoryList.forEach(product -> updateStatus(distributor, productCodeToCount, existingInventoryMap, updatedInventoryMap, deletedProducts,
                                                       product));

        rateUpdates.forEach(product -> updateRateAndScheme(existingInventoryMap, updatedInventoryMap, product));

        stockUpdates.forEach(product -> updateAvailability(productCodeToCount, existingInventoryMap, updatedInventoryMap, product));

        if(!deletedProducts.isEmpty()){
            sendDeletedProductsNotification(deletedProducts, distributor.getBusinessName());
        }

        return new ArrayList<>(updatedInventoryMap.values());
    }

    private void updateStatus(User distributor,
                              Map<String, Integer> productCodeToCount,
                              Map<String, InventoryAvailability> existingInventoryMap,
                              Map<String, InventoryAvailability> updatedInventoryMap,
                              Map<String, String> deletedProducts,
                              MargInventoryProduct product) {
        if(existingInventoryMap.containsKey(product.getCode())){
            InventoryAvailability inventoryAvailability = existingInventoryMap.get(product.getCode());
            if(product.getIs_Deleted().equals(Constants.MARG_DELETED_PRODUCT_FLAG) && !productCodeToCount.containsKey(
                product.getCode())){
                inventoryAvailability.setActiveStatus(false);
                inventoryAvailability.setAvailability(Constants.DELETED_PRODUCT_AVAILABILITY);
                updatedInventoryMap.put(product.getCode(), inventoryAvailability);
            } else if(product.getIs_Deleted().equals(
                Constants.MARG_DELETED_PRODUCT_FLAG) && productCodeToCount.containsKey(
                product.getCode())){
                inventoryAvailability.setAvailability(Constants.DELETED_PRODUCT_AVAILABILITY);
                updatedInventoryMap.put(product.getCode(), inventoryAvailability);
                deletedProducts.put(product.getCode(), product.getName());
            } else{
                InventoryAvailability updatedInventory = prepareInventoryObject(product, existingInventoryMap.get(
                    product.getCode()), distributor);
                if(productCodeToCount.containsKey(product.getCode())){
                    int newStock = updatedInventory.getAvailability();
                    int existingStock = productCodeToCount.get(product.getCode());
                    updatedInventory.setAvailability(newStock - existingStock);
                }
                updatedInventoryMap.put(product.getCode(), updatedInventory);
            }
        } else{
            InventoryAvailability inventoryAvailability =
                prepareInventoryObject(product, new InventoryAvailability(), distributor);
            existingInventoryMap.put(inventoryAvailability.getProductCode(), inventoryAvailability);
            updatedInventoryMap.put(inventoryAvailability.getProductCode(), inventoryAvailability);
        }
    }

    private void existingInventory(Map<String, InventoryAvailability> existingInventoryMap, User distributor) {
        List<InventoryAvailability> existingInventory =
            inventoryAvailabilityRepository.findAllBySupplierAndActiveStatus(distributor, true);
        existingInventory.forEach(inventory -> existingInventoryMap.put(inventory.getProductCode(), inventory));
    }

    private InventoryAvailability prepareInventoryObject(MargInventoryProduct inventoryProduct,
                                                         InventoryAvailability inventoryAvailability,
                                                         User supplier) {
        if(inventoryAvailability == null){
            inventoryAvailability = new InventoryAvailability();
        }
        inventoryAvailability.setActiveStatus(true);
        inventoryAvailability.setProductName(inventoryProduct.getName());
        inventoryAvailability.setSearchPrefixes(getSearchPrefixes(inventoryProduct.getName()));
        inventoryAvailability.setProductCode(inventoryProduct.getCode());

        if(Integer.parseInt(inventoryProduct.getDeal()) > 0){
            final Scheme scheme = new Scheme();
            scheme.setDeal(Integer.parseInt(inventoryProduct.getDeal()));
            scheme.setFree(Integer.parseInt(inventoryProduct.getFree()));
            inventoryAvailability.setSchemes(new ArrayList<>(List.of(scheme)));
            inventoryAvailability.setDeal(Integer.parseInt(inventoryProduct.getDeal()));
            inventoryAvailability.setFree(Integer.parseInt(inventoryProduct.getFree()));
        } else{
            inventoryAvailability.setSchemes(new ArrayList<>());
            inventoryAvailability.setDeal(0);
            inventoryAvailability.setFree(0);
        }

        inventoryAvailability.setPtr(Double.parseDouble(inventoryProduct.getRate()));
        inventoryAvailability.setPts(Double.parseDouble(inventoryProduct.getPurchaseRate()));
        inventoryAvailability.setMrp(Double.parseDouble(inventoryProduct.getMRP()));

        inventoryAvailability.setSupplier(supplier);
        inventoryAvailability.setAvailability((int) Double.parseDouble(inventoryProduct.getStock()));
        if(inventoryAvailability.getCreationDateTime() == null){
            inventoryAvailability.setCreationDateTime(DateTimeUtils.dateTimeInIST().toLocalDateTime());
        }
        inventoryAvailability.setModificationDateTime(DateTimeUtils.dateTimeInIST().toLocalDateTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate expiryDate = null;
        try {
            expiryDate = LocalDate.parse(inventoryProduct.getExp(), formatter);
        } catch (DateTimeParseException e) {
            inventoryAvailability.setExpiryDate(null);
        }
        inventoryAvailability.setExpiryDate(expiryDate);
        inventoryAvailability.setDivision(inventoryProduct.getCompany());

        return inventoryAvailability;
    }

    @Transactional
    public void processInventoryV2(List<ProfitMakerInventoryProductV2> products, final User distributor,
                                   Map<String, Integer> productCodeToCount) {
        List<InventoryAvailability> inventoryList = new ArrayList<>();

        List<InventoryAvailability> existingInventory =
            inventoryAvailabilityRepository.findAllBySupplierAndActiveStatus(distributor, true);
        Map<String, InventoryAvailability> existingInventoryMap = new HashMap<>();
        existingInventory.forEach(inventory -> existingInventoryMap.put(inventory.getProductCode(), inventory));

        Map<String, String> deletedProducts = new HashMap<>();

        products.forEach(product -> {
            String productCode = product.getCode();
            if(!existingInventoryMap.isEmpty() && existingInventoryMap.containsKey(productCode)){
                InventoryAvailability inventoryAvailability = existingInventoryMap.get(productCode);
                if(product.isDeleted() && !productCodeToCount.containsKey(String.valueOf(product.getCode()))){
                    inventoryAvailability.setActiveStatus(false);
                    inventoryAvailability.setAvailability(Constants.DELETED_PRODUCT_AVAILABILITY);
                    inventoryList.add(inventoryAvailability);
                } else if(product.isDeleted() && productCodeToCount.containsKey(String.valueOf(product.getCode()))){
                    inventoryAvailability.setAvailability(Constants.DELETED_PRODUCT_AVAILABILITY);
                    inventoryList.add(inventoryAvailability);
                    deletedProducts.put(String.valueOf(product.getCode()),
                                        String.join(" ", product.getName(), product.getPacking()));
                } else{
                    InventoryAvailability updatedInventory =
                        prepareInventoryObjectV2(product, inventoryAvailability, distributor);
                    if(productCodeToCount.containsKey(String.valueOf(product.getCode()))){
                        int newStock = updatedInventory.getAvailability();
                        int existingStock = productCodeToCount.get(String.valueOf(product.getCode()));
                        updatedInventory.setAvailability(newStock - existingStock);
                    }
                    inventoryList.add(updatedInventory);
                }
            } else{
                inventoryList.add(prepareInventoryObjectV2(product, new InventoryAvailability(), distributor));
            }
        });

        if(!deletedProducts.isEmpty()){
            sendDeletedProductsNotification(deletedProducts, distributor.getBusinessName());
        }

        if(!inventoryList.isEmpty()){
            inventoryAvailabilityRepository.saveAll(inventoryList);
        }
    }

    private InventoryAvailability prepareInventoryObject(final ProfitMakerInventoryProduct inventoryProduct,
                                                         InventoryAvailability inventoryAvailability,
                                                         final User supplier) {
        inventoryAvailability.setActiveStatus(true);
        inventoryAvailability.setProductName(inventoryProduct.getName());
        inventoryAvailability.setPacking(inventoryProduct.getPacking());
        inventoryAvailability.setProductCode(String.valueOf(inventoryProduct.getRid()));

        if(inventoryProduct.getDeal() > 0){
            final Scheme scheme = new Scheme();
            scheme.setDeal((int) Math.round(inventoryProduct.getDeal()));
            scheme.setFree((int) inventoryProduct.getFree());
            inventoryAvailability.setSchemes(new ArrayList<>(List.of(scheme)));
            inventoryAvailability.setDeal((int) Math.round(inventoryProduct.getDeal()));
            inventoryAvailability.setFree((int) inventoryProduct.getFree());
        } else{
            inventoryAvailability.setSchemes(new ArrayList<>());
            inventoryAvailability.setDeal(0);
            inventoryAvailability.setFree(0);
        }

        inventoryAvailability.setPtr(inventoryProduct.getRate());
        inventoryAvailability.setMrp(inventoryProduct.getMrp());
        inventoryAvailability.setSupplier(supplier);
        inventoryAvailability.setAvailability(inventoryProduct.getStock());
        if(inventoryAvailability.getCreationDateTime() == null){
            inventoryAvailability.setCreationDateTime(DateTimeUtils.dateTimeInIST().toLocalDateTime());
        }
        inventoryAvailability.setModificationDateTime(DateTimeUtils.dateTimeInIST().toLocalDateTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate expiryDate = null;
        try {
            expiryDate = LocalDate.parse(inventoryProduct.getExpiryDate(), formatter);
        } catch (DateTimeParseException e) {
            inventoryAvailability.setExpiryDate(null);
        }
        inventoryAvailability.setExpiryDate(expiryDate);
        inventoryAvailability.setDivision(inventoryProduct.getCompany());

        return inventoryAvailability;
    }

    private InventoryAvailability prepareInventoryObjectV2(final ProfitMakerInventoryProductV2 inventoryProduct,
                                                           InventoryAvailability inventoryAvailability,
                                                           final User supplier) {
        inventoryAvailability.setActiveStatus(true);
        inventoryAvailability.setProductName(inventoryProduct.getName());
        inventoryAvailability.setSearchPrefixes(getSearchPrefixes(inventoryProduct.getName()));
        inventoryAvailability.setPacking(inventoryProduct.getPacking());
        inventoryAvailability.setProductCode(String.valueOf(inventoryProduct.getCode()));

        if(inventoryProduct.getDeal() > 0){
            final Scheme scheme = new Scheme();
            scheme.setDeal((int) Math.round(inventoryProduct.getDeal()));
            scheme.setFree((int) inventoryProduct.getFree());
            inventoryAvailability.setSchemes(new ArrayList<>(List.of(scheme)));
            inventoryAvailability.setDeal((int) Math.round(inventoryProduct.getDeal()));
            inventoryAvailability.setFree((int) inventoryProduct.getFree());
        } else{
            inventoryAvailability.setSchemes(new ArrayList<>());
            inventoryAvailability.setDeal(0);
            inventoryAvailability.setFree(0);
        }

        inventoryAvailability.setPtr(inventoryProduct.getRate());
        inventoryAvailability.setMrp(inventoryProduct.getMrp());
        inventoryAvailability.setSupplier(supplier);
        inventoryAvailability.setAvailability(inventoryProduct.getStock());
        if(inventoryAvailability.getCreationDateTime() == null){
            inventoryAvailability.setCreationDateTime(DateTimeUtils.dateTimeInIST().toLocalDateTime());
        }
        inventoryAvailability.setModificationDateTime(DateTimeUtils.dateTimeInIST().toLocalDateTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate expiryDate = null;
        try {
            expiryDate = LocalDate.parse(inventoryProduct.getExpiryDate(), formatter);
        } catch (DateTimeParseException e) {
            inventoryAvailability.setExpiryDate(null);
        }
        inventoryAvailability.setExpiryDate(expiryDate);
        inventoryAvailability.setDivision(inventoryProduct.getCompany());

        return inventoryAvailability;
    }

    private void sendDeletedProductsNotification(final Map<String, String> deletedProducts, final String businessName) {
        final String tdStart = "<td>";
        final String tdEnd = "</td>";
        final String tableStart = "<table style='max-width: 20em;'>"
                                  + "<thead style='text-align: left;'>"
                                  + "<tr>"
                                  + "<td><h4>Product Code</h4></td>"
                                  + "<td>&nbsp;&nbsp;</td>"
                                  + "<td><h4>Product Name</h4></td>"
                                  + "</tr>"
                                  + "</thead><tbody>";
        final String tableEnd = "</tbody></table>";

        final String header = "<h4 style='margin:0;'>List of deleted products having pending or invoicing orders</h4>";
        final StringBuilder tableRow = new StringBuilder();
        deletedProducts.forEach((productCode, productName) -> {
            tableRow.append("<tr>");
            tableRow.append(tdStart).append(productCode).append(tdEnd);
            tableRow.append(tdStart + "&nbsp;&nbsp;" + tdEnd);
            tableRow.append(tdStart).append(productName).append(tdEnd);
            tableRow.append("</tr>");
        });

        String message = header
                         + tableStart
                         + tableRow
                         + tableEnd;

        emailUtils.sendEmail("Inventory Update Warning : " + businessName, message, Constants.NOTIFICATIONS_EMAIL);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateInventoryAndJobExecutionReport(MargTransactionUpdate margTransactionUpdate) {

        List<InventoryAvailability> updatedInventory = margTransactionUpdate.getInventoryAvailabilityList();

        if(updatedInventory != null && !updatedInventory.isEmpty()){
            inventoryAvailabilityRepository.saveAll(updatedInventory);
        }

        JobExecutionReport jobExecutionReport = margTransactionUpdate.getJobExecutionReport();

        if(jobExecutionReport != null){
            jobExecutionReportRepository.save(jobExecutionReport);
        }

    }
}
