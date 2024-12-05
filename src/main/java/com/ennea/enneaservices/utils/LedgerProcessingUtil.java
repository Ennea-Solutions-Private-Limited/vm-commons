package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.constants.Constants;
import com.ennea.enneaservices.model.Dto.Marg.MargLedger;
import com.ennea.enneaservices.model.Dto.ProfitMaker.ProfitMakerLedger;
import com.ennea.enneaservices.model.Ledger;
import com.ennea.enneaservices.model.SupplierCustomer;
import com.ennea.enneaservices.model.User;
import com.ennea.enneaservices.repository.JobExecutionReportRepository;
import com.ennea.enneaservices.repository.LedgerRepository;
import com.ennea.enneaservices.repository.RequestStatusRepository;
import com.ennea.enneaservices.repository.SupplierCustomerRepository;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class LedgerProcessingUtil {

    private static final String MARG_IS_DELETED_FLAG = "1";
    private static final String DELETED_PARTY_CODE = null;
    private static final int DELETED_PARTY_BALANCE = 0;
    private final Logger logger = LoggerFactory.getLogger(LedgerProcessingUtil.class);

    private final LedgerRepository ledgerRepository;

    private final SupplierCustomerRepository supplierCustomerRepository;

    private final RequestStatusRepository requestStatusRepository;

    private final JobExecutionReportRepository jobExecutionReportRepository;

    @Autowired
    public LedgerProcessingUtil(LedgerRepository ledgerRepository,
                                SupplierCustomerRepository supplierCustomerRepository,
                                RequestStatusRepository requestStatusRepository,
                                JobExecutionReportRepository jobExecutionReportRepository) {
        this.ledgerRepository = ledgerRepository;
        this.supplierCustomerRepository = supplierCustomerRepository;
        this.requestStatusRepository = requestStatusRepository;
        this.jobExecutionReportRepository = jobExecutionReportRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processLedger(String decompressedResponse, User user) {
        if(StringUtils.isBlank(decompressedResponse)){
            logger.error("Decompressed response null for user: {}", user.getId());
            jobExecutionReportRepository.save(
                JobExecutionReportUtils.prepareJobExecutionReport(user, Constants.JOB_TYPE_LEDGER,
                                                                  Constants.JOB_STATUS_FAILED,
                                                                  DateTimeUtils.dateTimeInIST().toLocalDateTime(),
                                                                  Constants.NO_RECORDS_MODIFIED, "Response null"));
            return;
        }
        int modifiedRecords;
        List<MargLedger> updatedMargLedgers = new ArrayList<>();
        try {
            JSONObject responseObj = new JSONObject(decompressedResponse);
            JSONObject details = responseObj.getJSONObject("Details");
            JSONArray parties = details.getJSONArray("Party");

            for(int i = 0; i < parties.length(); i++){
                JSONObject obj = parties.getJSONObject(i);
                MargLedger margLedger = prepareMargLedger(obj);
                if(margLedger != null){
                    updatedMargLedgers.add(margLedger);
                }
            }

            modifiedRecords = parties.length();
        } catch (JSONException e) {
            logger.error("Unable to read ledger response for distributor : {}", user.getId(), e);
            final String message = JobExecutionReportUtils.prepareFailureStackTrace(e);
            jobExecutionReportRepository.save(JobExecutionReportUtils.prepareJobExecutionReport(user,
                                                                                                Constants.JOB_TYPE_LEDGER,
                                                                                                Constants.JOB_STATUS_FAILED,
                                                                                                DateTimeUtils.dateTimeInIST()
                                                                                                    .toLocalDateTime(),
                                                                                                Constants.NO_RECORDS_MODIFIED,
                                                                                                message));
            return;
        }
        try {
            processMargLedger(updatedMargLedgers, user);
        } catch (Exception e) {
            logger.error("Unable to process ledger for distributor : {}", user.getId(), e);
            final String message = JobExecutionReportUtils.prepareFailureStackTrace(e);
            jobExecutionReportRepository.save(JobExecutionReportUtils.prepareJobExecutionReport(user,
                                                                                                Constants.JOB_TYPE_LEDGER,
                                                                                                Constants.JOB_STATUS_FAILED,
                                                                                                DateTimeUtils.dateTimeInIST()
                                                                                                    .toLocalDateTime(),
                                                                                                Constants.NO_RECORDS_MODIFIED,
                                                                                                message));
            return;
        }
        jobExecutionReportRepository.save(JobExecutionReportUtils.prepareJobExecutionReport(user,
                                                                                            Constants.JOB_TYPE_LEDGER,
                                                                                            Constants.JOB_STATUS_SUCCESS,
                                                                                            DateTimeUtils.dateTimeInIST()
                                                                                                .toLocalDateTime(),
                                                                                            modifiedRecords,
                                                                                            null));
    }

    private MargLedger prepareMargLedger(JSONObject obj) throws JSONException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(obj.toString(), MargLedger.class);
        } catch (Exception e) {
            logger.error("Error while converting json to marg inventory product, {}", e.toString());
            return null;
        }
    }

    public void processMargLedger(List<MargLedger> updatedMargLedgers, User user) {
        List<Ledger> updatedLedgers = new ArrayList<>();

        List<Ledger> existingLedgers = ledgerRepository.findAllBySupplier(user);
        List<SupplierCustomer> supplierCustomers = supplierCustomerRepository
            .findSupplierCustomerListBySupplierAndRequestStatus(user,
                                                                requestStatusRepository.findRequestStatusByStatus(
                                                                    Constants.CHEMIST_REQUEST_STATUS_APPROVED));

        // marg ledgers with party code as key
        Map<String, MargLedger> updatedMargLedgersMap = new HashMap<>();
        updatedMargLedgers.forEach(updatedMargLedger -> updatedMargLedgersMap.put(updatedMargLedger.getRid(), updatedMargLedger));

        Map<String, String> updatedMargLedgersAliasToPartyCode = new HashMap<>();
        updatedMargLedgers.forEach(updatedMargLedger -> updatedMargLedgersAliasToPartyCode.put(updatedMargLedger.getLedgerCode(), updatedMargLedger.getRid()));

        // existing ledgers in the database with partycode
        Map<String, Ledger> existingLedgersMap = new HashMap<>();
        existingLedgers.forEach(existingLedger -> existingLedgersMap.put(existingLedger.getPartyCode(), existingLedger));

        Map<String, String> existingMargLedgersPartyCodeToAlias = new HashMap<>();
        existingLedgers.forEach(existingMargLedger -> existingMargLedgersPartyCodeToAlias.put(existingMargLedger.getPartyCode(), existingMargLedger.getAlias()));

        Map<String, Ledger> existingLedgersMapWithAliasKey = new HashMap<>();
        existingLedgers.forEach(existingLedger -> existingLedgersMapWithAliasKey.put(existingLedger.getAlias(), existingLedger));

        // existing supplier_customer with party code as the key
        Map<String, SupplierCustomer> supplierCustomerMap = new HashMap<>();
        supplierCustomers.forEach(supplierCustomer -> supplierCustomerMap.put(supplierCustomer.getCustomerPartyCode(), supplierCustomer));

        List<Ledger> toBeDeletedLedgers = new ArrayList<>();

        if(!existingLedgersMap.isEmpty()){
            updatedMargLedgersMap.forEach((partyCode, updatedMargLedger) -> {
                Ledger updatedLedger;
                if(existingLedgersMap.containsKey(partyCode)
                   && !updatedMargLedger.getIsDeleted().equals(MARG_IS_DELETED_FLAG)){
                    prepareLedgerObject(updatedMargLedger, existingLedgersMap.get(partyCode), user);
                    existingLedgersMap.remove(partyCode);
                } else if(existingLedgersMap.containsKey(partyCode)
                          && updatedMargLedger.getIsDeleted().equals(MARG_IS_DELETED_FLAG)){
                    toBeDeletedLedgers.add(existingLedgersMap.get(partyCode));
                    existingLedgersMap.remove(partyCode);
                } else if(existingLedgersMapWithAliasKey.containsKey(updatedMargLedger.getLedgerCode())
                          && !updatedMargLedger.getIsDeleted().equals(MARG_IS_DELETED_FLAG)){
                    updatedLedger = prepareLedgerObject(updatedMargLedger,
                                                        existingLedgersMapWithAliasKey.get(updatedMargLedger.getLedgerCode()),
                                                        user);
                    existingLedgersMap.remove(existingLedgersMapWithAliasKey.get(updatedMargLedger.getLedgerCode())
                                                  .getPartyCode());
                } else if(existingLedgersMapWithAliasKey.containsKey(updatedMargLedger.getLedgerCode())
                          && updatedMargLedger.getIsDeleted().equals(MARG_IS_DELETED_FLAG)){
                    toBeDeletedLedgers.add(existingLedgersMapWithAliasKey.get(updatedMargLedger.getLedgerCode()));
                    existingLedgersMap.remove(existingLedgersMapWithAliasKey.get(updatedMargLedger.getLedgerCode())
                                                  .getPartyCode());
                } else{
                    updatedLedger = prepareLedgerObject(updatedMargLedger, new Ledger(), user);
                    updatedLedgers.add(updatedLedger);
                }
            });

            existingLedgersMap.forEach((partyCode, existingLedger) -> toBeDeletedLedgers.add(existingLedger));
        } else{
            updatedMargLedgersMap.forEach((partyCode, updatedMargLedger) -> {
                if(!updatedMargLedger.getIsDeleted().equals(MARG_IS_DELETED_FLAG)){
                    Ledger updatedLedger = prepareLedgerObject(updatedMargLedger, new Ledger(), user);
                    updatedLedgers.add(updatedLedger);
                }
            });
        }

        supplierCustomerMap.forEach((partyCode, supplierCustomer) -> {
            String olderAlias = existingMargLedgersPartyCodeToAlias.get(supplierCustomer.getCustomerPartyCode());
            if(updatedMargLedgersMap.containsKey(partyCode)
               && !updatedMargLedgersMap.get(partyCode).getIsDeleted().equals(MARG_IS_DELETED_FLAG)){
                supplierCustomer.setOutstandingAmount(Double.parseDouble(updatedMargLedgersMap.get(partyCode)
                                                                             .getBalance()));
                String partyName = updatedMargLedgersMap.get(partyCode).getName();
                String ledgerCode = updatedMargLedgersMap.get(partyCode).getLedgerCode();
                if(StringUtils.isNotBlank(ledgerCode)){
                    partyName = partyName.replace(ledgerCode, "").trim();
                    partyName = ledgerCode + " • " + partyName;
                }
                supplierCustomer.setCustomerPartyName(partyName);
            } else if(olderAlias != null
                      && updatedMargLedgersAliasToPartyCode.containsKey(olderAlias)
                      && !updatedMargLedgersMap.get(updatedMargLedgersAliasToPartyCode.get(olderAlias))
                .getIsDeleted()
                .equals(MARG_IS_DELETED_FLAG)){
                supplierCustomer.setOutstandingAmount(Double.parseDouble(updatedMargLedgersMap.get(
                    updatedMargLedgersAliasToPartyCode.get(olderAlias)).getBalance()));
                MargLedger updatedLedger = updatedMargLedgersMap.get(updatedMargLedgersAliasToPartyCode.get(
                    olderAlias));

                String partyName = updatedLedger.getName();
                String ledgerCode = updatedLedger.getLedgerCode();
                if(StringUtils.isNotBlank(ledgerCode)){
                    partyName = partyName.replace(ledgerCode, "").trim();
                    partyName = ledgerCode + " • " + partyName;
                }
                supplierCustomer.setCustomerPartyCode(updatedMargLedgersAliasToPartyCode.get(olderAlias));
                supplierCustomer.setCustomerPartyName(partyName);
            } else{
                supplierCustomer.setCustomerPartyCode(DELETED_PARTY_CODE);
                supplierCustomer.setOutstandingAmount(DELETED_PARTY_BALANCE);
            }
        });

        if(!toBeDeletedLedgers.isEmpty()){
            ledgerRepository.deleteAll(toBeDeletedLedgers);
        }

        if(!updatedLedgers.isEmpty()){
            ledgerRepository.saveAll(updatedLedgers);
        }
    }

    @Transactional
    public void processProfitMakerLedger(List<ProfitMakerLedger> updatedProfitMakerLedgers, User user) {
        List<Ledger> updatedLedgers = new ArrayList<>();
        List<SupplierCustomer> updatedDistributorChemists = new ArrayList<>();

        List<Ledger> existingLedgers = ledgerRepository.findAllBySupplier(user);
        List<SupplierCustomer> supplierCustomers = supplierCustomerRepository
            .findSupplierCustomerListBySupplierAndRequestStatus(user,
                                                                requestStatusRepository.findRequestStatusByStatus(
                                                                    Constants.CHEMIST_REQUEST_STATUS_APPROVED));

        Map<String, ProfitMakerLedger> updatedProfitMakerLedgersMap = new HashMap<>();
        updatedProfitMakerLedgers.forEach(updatedMargLedger -> updatedProfitMakerLedgersMap.put(String.valueOf(updatedMargLedger.getRid()), updatedMargLedger));

        Map<String, Ledger> existingLedgersMap = new HashMap<>();
        existingLedgers.forEach(existingLedger -> existingLedgersMap.put(existingLedger.getPartyCode(), existingLedger));

        Map<String, SupplierCustomer> distributorChemistsMap = new HashMap<>();
        supplierCustomers.forEach(distributorChemist -> distributorChemistsMap.put(distributorChemist.getCustomerPartyCode(), distributorChemist));

        if(!existingLedgersMap.isEmpty()){
            updatedProfitMakerLedgersMap.forEach((partyCode, updatedProfitMakerLedger) -> {
                Ledger updatedLedger;
                if(existingLedgersMap.containsKey(partyCode) && !updatedProfitMakerLedger.isDeleted()){
                    updatedLedger =
                        prepareLedgerObject(updatedProfitMakerLedger, existingLedgersMap.get(partyCode), user);
                    updatedLedgers.add(updatedLedger);
                } else if(existingLedgersMap.containsKey(partyCode) && updatedProfitMakerLedger.isDeleted()){
                    ledgerRepository.delete(existingLedgersMap.get(partyCode));
                } else{
                    updatedLedger = prepareLedgerObject(updatedProfitMakerLedger, new Ledger(), user);
                    updatedLedgers.add(updatedLedger);
                }
            });
        } else{
            updatedProfitMakerLedgersMap.forEach((partyCode, updatedProfitMakerLedger) -> {
                if(!updatedProfitMakerLedger.isDeleted()){
                    Ledger updatedLedger = prepareLedgerObject(updatedProfitMakerLedger, new Ledger(), user);
                    updatedLedgers.add(updatedLedger);
                }
            });
        }

        distributorChemistsMap.forEach((partyCode, distributorChemist) -> {
            if(updatedProfitMakerLedgersMap.containsKey(partyCode)){
                if(!updatedProfitMakerLedgersMap.get(partyCode).isDeleted()){
                    distributorChemist.setOutstandingAmount(updatedProfitMakerLedgersMap.get(partyCode).getBalance());
                    updatedDistributorChemists.add(distributorChemist);
                } else{
                    distributorChemist.setCustomerPartyCode(DELETED_PARTY_CODE);
                    distributorChemist.setOutstandingAmount(DELETED_PARTY_BALANCE);
                }
            }
        });

        if(!updatedLedgers.isEmpty()){
            ledgerRepository.saveAll(updatedLedgers);
        }

        if(!updatedDistributorChemists.isEmpty()){
            supplierCustomerRepository.saveAll(updatedDistributorChemists);
        }
    }

    private Ledger prepareLedgerObject(MargLedger margLedger, Ledger ledger, User distributor) {
        ledger.setSupplier(distributor);
        ledger.setPartyCode(margLedger.getRid());
        double balance;
        try {
            balance = Double.parseDouble(margLedger.getBalance());
        } catch (Exception e) {
            balance = 0.0;
        }
        ledger.setBalance(balance);
        ledger.setActive(true);

        StringBuilder partyName = new StringBuilder();
        if(!margLedger.getLedgerCode().isEmpty()){
            ledger.setAlias(margLedger.getLedgerCode());
            partyName.append(margLedger.getLedgerCode());
            partyName.append(" ");
        }
        partyName.append(margLedger.getName());

        ledger.setPartyName(partyName.toString());
        ledger.setAddress(margLedger.getAddress());

        String phoneNumber =
            Stream.of(margLedger.getPhone1(), margLedger.getPhone2(), margLedger.getPhone3(), margLedger.getPhone4())
                .filter(s -> s != null && !s.isEmpty())
                .findFirst()
                .orElse("");
        ledger.setPhoneNumber(phoneNumber);

        ledger.setDrugLicense(margLedger.getDlNo());
        ledger.setGstin(margLedger.getGSTIN());
        ledger.setBank(margLedger.getBank());
        ledger.setBranch(margLedger.getBranch());

        if(ledger.getCreationDateTime() == null){
            ledger.setCreationDateTime(LocalDateTime.now());
        }
        ledger.setModificationDateTime(LocalDateTime.now());
        return ledger;
    }

    private Ledger prepareLedgerObject(ProfitMakerLedger profitMakerLedger, Ledger ledger, User distributor) {
        ledger.setSupplier(distributor);
        ledger.setPartyCode(String.valueOf(profitMakerLedger.getRid()));
        ledger.setActive(true);
        ledger.setPartyName(profitMakerLedger.getName());
        ledger.setAlias(profitMakerLedger.getLedgerCode());
        ledger.setAddress(profitMakerLedger.getAddress());
        ledger.setPhoneNumber(profitMakerLedger.getPhone());
        ledger.setDrugLicense(profitMakerLedger.getDlNo());
        ledger.setGstin(profitMakerLedger.getGstin());
        ledger.setBank(profitMakerLedger.getBank());
        ledger.setBranch(profitMakerLedger.getBranch());

        if(ledger.getCreationDateTime() == null){
            ledger.setCreationDateTime(LocalDateTime.now());
        }
        ledger.setModificationDateTime(LocalDateTime.now());
        return ledger;
    }
}
