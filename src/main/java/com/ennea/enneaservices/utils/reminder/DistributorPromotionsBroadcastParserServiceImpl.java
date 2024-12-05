package com.ennea.enneaservices.utils.reminder;

import com.ennea.enneaservices.model.Broadcast;
import com.ennea.enneaservices.model.BroadcastTemplate;
import com.ennea.enneaservices.model.whatsapp.WhatsappTemplateParameterText;
import com.ennea.enneaservices.utils.DateTimeUtils;
import com.ennea.enneaservices.utils.ParserUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DistributorPromotionsBroadcastParserServiceImpl implements BroadcastParserService {

    @Override
    public Broadcast getBroadcast(BroadcastTemplate template, Map<String, String> oldRow) {
        Broadcast broadcast = createNewBroadcast(new Broadcast(), oldRow);
        String columnNameValue;
        if(template.getTemplateName().equals("valuemedi_distributor_product_promotion")){
            columnNameValue = oldRow.get("Product Name");
        } else{
            columnNameValue = oldRow.get("Division Name");
        }

        broadcast.getTemplateVariables().add(new WhatsappTemplateParameterText(columnNameValue));
        broadcast.getTemplateVariables().add(new WhatsappTemplateParameterText(oldRow.get("supplier")));
        broadcast.getTemplateVariables().add(new WhatsappTemplateParameterText(oldRow.get("supplierNumber")));

        broadcast.setDoNotProcess(true);
        return broadcast;
    }

    private Broadcast createNewBroadcast(Broadcast broadcast, Map<String, String> oldRow) {
        broadcast.setPartyCode(ParserUtils.removePrefixZeros(oldRow.get("Party code")));
        broadcast.setPartyName(oldRow.get("Party name"));
        broadcast.setDate(DateTimeUtils.dateTimeInIST().toLocalDateTime());
        broadcast.setPhoneNumber(oldRow.get("Phone number")
                                 != null ? Long.parseLong(oldRow.get("Phone number")) : null);
        broadcast.setEmailId(oldRow.get("Email id") != null ? oldRow.get("Email id") : null);
        return broadcast;
    }
}
