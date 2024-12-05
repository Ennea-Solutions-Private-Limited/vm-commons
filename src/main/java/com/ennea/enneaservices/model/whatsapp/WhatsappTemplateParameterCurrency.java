package com.ennea.enneaservices.model.whatsapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhatsappTemplateParameterCurrency implements WhatsappTemplateParameter {

    private String type = Type.currency.toString();
    private currency currency = new currency();


    public WhatsappTemplateParameterCurrency(String amount) { // Multiply by 1000 before calling
        this.currency.amount_1000 = amount;
    }

    public WhatsappTemplateParameterCurrency(Double amount) { // Do not multiply by 1000 before calling
        this.currency.amount_1000 = doubleToWhatsappIntegerString(amount);
    }

    private static String doubleToWhatsappIntegerString(Double doubleVal) {
        DecimalFormat df = new DecimalFormat("#.000");
        return df.format(doubleVal).replace(".", "");
    }

    static class currency {
        public String fallback_value = "VALUE";
        public String code = "INR";
        public String amount_1000;
    }

}
