package com.ennea.enneaservices.utils;

import org.springframework.stereotype.Component;

@Component
public class FileValidatorUtility {

    public static boolean isValidCsvFile(String fileName) {
        return fileName.toLowerCase().endsWith(".csv");
    }

}