package com.ennea.enneaservices.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ParserUtils {

    public static String removePrefixZeros(String input) {
        try {
            return StringUtils.stripStart(input, "0");
        } catch (Exception e) {
            log.error("unable to parse input : {}", input);
            return null;
        }
    }

    public static String removeCommas(String input) {
        if(StringUtils.isNotBlank(input)){
            return input.replace(",", "");
        }
        return input;
    }

    public static Double parseDouble(String input, Double defaultValue) {
        try {
            return Double.parseDouble(input);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Float parseFloat(String input, Float defaultValue) {
        try {
            return Float.parseFloat(input);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
