package com.ennea.enneaservices.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class DateTimeUtils {

    public static ZonedDateTime dateTimeInIST() {
        LocalDateTime localNow = LocalDateTime.now();
        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
        return zonedUTC.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
    }


    public static LocalDate parseDate(final String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
//            temporary comment this out for removing excessive logs
//            log.info("Failed to parse date with format: {}", date);
            return null;
        }
    }

    public static LocalDateTime parseDateTime(final String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    public static String convertDateTimeToString(final LocalDateTime localDateTime, String pattern) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return formatter.format(localDateTime);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCurrentDate(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

}
