package com.ennea.enneaservices.model.whatsapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class WhatsappTemplateParameterDateTime implements WhatsappTemplateParameter {

    @JsonIgnore
    private String datePattern = "dd MMMM uuuu";
    @JsonIgnore
    private List<String> dateDelimiters = new ArrayList<>() {{
        add("-");
        add("/");
        add(".");
    }};
    @JsonIgnore
    private String standardDatePattern = "uuuu?MM?dd";
    private String type = Type.date_time.toString();
    private datetime date_time = new datetime();

    public WhatsappTemplateParameterDateTime() {
        this.date_time.fallback_value =
            DateTimeFormatter.ofPattern(datePattern).format(LocalDate.now(ZoneId.of("Asia/Kolkata")));
    }

    public WhatsappTemplateParameterDateTime(LocalDate date) {
        this.date_time.fallback_value = DateTimeFormatter.ofPattern(datePattern).format(date);
    }

    public WhatsappTemplateParameterDateTime(String date) {
        this.date_time.fallback_value = date;
    }

    public WhatsappTemplateParameterDateTime(String date, Boolean isUnknownPattern) {
        if(isUnknownPattern){
            String delimiter = "";
            for(String delim : dateDelimiters){
                if(date.contains(delim)){
                    delimiter = delim;
                    break;
                }
            }
            String localPattern = standardDatePattern.replace("?", delimiter);
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(localPattern));
            this.date_time.fallback_value = DateTimeFormatter.ofPattern("dd MMMM uuuu").format(localDate);
        } else{
            this.date_time.fallback_value = date;
        }
    }

    static class datetime {
        public String fallback_value;
            // DateTime text value --- recommendation ---> Expressed in "Month Day, Year" format
    }
}
