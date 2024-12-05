package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.enums.ErrorEnum;
import com.ennea.enneaservices.exceptions.CustomEnneaExeption;
import com.ennea.enneaservices.model.Dto.ApiError;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5;

@Slf4j
@Component
public class ApiConnectChecksumUtils {

    //Using a LinkedHashMap to maintain order. If there are any changes in the interface, then we'd have to maintain the same order.
    public static String generateCheckSum(@NonNull final LinkedHashMap<String, Object> requestMap) {
        final StringBuilder rawCheckSum = new StringBuilder();
        generateRawCheckSum(requestMap, rawCheckSum);
        return encodeCheckSumWithSHA256(rawCheckSum.toString().trim());
    }

    private static void generateRawCheckSum(final LinkedHashMap<String, Object> requestMap,
                                            final StringBuilder rawCheckSum) {
        if(null == requestMap){
            return;
        }

        for(Map.Entry<String, Object> entry : requestMap.entrySet()){
            if(!entry.getKey().equals("checksum")){
                if(entry.getValue() instanceof List){
                    List<Object> tempLst = ((List) entry.getValue());
                    if(!CollectionUtils.isEmpty(tempLst) && (tempLst.get(0) instanceof Map)){
                        List<? extends Map<String, Object>> innerObjectMap =
                            (List<? extends Map<String, Object>>) entry.getValue();

                        for(Map<String, Object> innerMap : innerObjectMap){
                            generateRawCheckSum((LinkedHashMap<String, Object>) innerMap, rawCheckSum);
                        }
                    } else if(!CollectionUtils.isEmpty(tempLst)){
                        for(Object strValues : tempLst){
                            rawCheckSum.append(
                                validateInfo(
                                    String.valueOf(strValues)));
                        }
                    }
                } else if(entry.getValue() instanceof Map){
                    Map<? extends String, ? extends Object> innerObjectMap2
                        = (Map<? extends String, ? extends Object>) entry.getValue();
                    for(Map.Entry<? extends String, ? extends Object> entryInn : innerObjectMap2.entrySet()){
                        rawCheckSum.append(
                            validateInfo(
                                String.valueOf(entryInn.getValue())));
                    }
                } else{
                    rawCheckSum.append(
                        validateInfo(
                            String.valueOf(entry.getValue())));
                }
            }
        }
    }

    private static Object validateInfo(String value) {
        return StringUtils.isNotEmpty(value) && !"null".equals(value) ? value : StringUtils.EMPTY;
    }

    private static String encodeCheckSumWithSHA256(final String data) {
        try {
            final MessageDigest md = MessageDigest.getInstance(MD5);
            final StringBuilder sb = new StringBuilder();
            md.update(data.getBytes(StandardCharsets.UTF_8));
            final byte[] hashBytes = md.digest();
            for(byte
                b : hashBytes){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (final NoSuchAlgorithmException e) {
            log.error("Unable to generate axis api connect checksum : {}", e.getMessage());
            final ApiError errorResponse =
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.UNABLE_TO_GENERATE_CHECKSUM.getMessage(),
                             ErrorEnum.UNABLE_TO_GENERATE_CHECKSUM.getCode(), null);
            throw new CustomEnneaExeption(errorResponse);
        }
    }
}