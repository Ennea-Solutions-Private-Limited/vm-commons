package com.ennea.enneaservices.utils.reminder;

import com.ennea.enneaservices.constants.Constants;
import com.ennea.enneaservices.model.Broadcast;
import com.ennea.enneaservices.model.BroadcastTemplate;
import com.ennea.enneaservices.model.whatsapp.WhatsappTemplateParameterText;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Service
public class DefaultBroadcastParserServiceImpl implements BroadcastParserService {
    @Override
    public Broadcast getBroadcast(BroadcastTemplate broadcastTemplate, Map<String, String> oldRow) {
        Broadcast broadcast = new Broadcast();

        for(String header : getExpectedHeaders(broadcastTemplate)){
            String recordValue = oldRow.get(header);
            if(StringUtils.isBlank(recordValue)){
                broadcast.setRowStatus("Blank Field");
                break;
            }
            if(header.equalsIgnoreCase(Constants.WHATSAPP_PROMO_FILE_NUMBER)){
                broadcast.setPhoneNumber(Long.parseLong(recordValue));
            } else if(header.startsWith(Constants.WHATSAPP_PROMO_FILE_HEADER)){
                broadcast.getTemplateHeaderVariables().add(new WhatsappTemplateParameterText(recordValue));
            } else if(header.startsWith(Constants.WHATSAPP_PROMO_FILE_BODY)){
                broadcast.getTemplateVariables().add(new WhatsappTemplateParameterText(recordValue));
            } else if(header.startsWith(Constants.WHATSAPP_PROMO_FILE_BUTTON)){
                broadcast.setButtonLink(recordValue);
            }
        }
        return broadcast;
    }

    private Set<String> getExpectedHeaders(BroadcastTemplate template) {
        Set<String> headers = new LinkedHashSet<>();
        headers.add(Constants.WHATSAPP_PROMO_FILE_NUMBER);
        if(StringUtils.isNotBlank(template.getHeaderType())){
            if(template.getHeaderType().equalsIgnoreCase(Constants.WHATSAPP_PROMO_TEXT)){
                for(int i = 1; i <= template.getHeaderVarCount(); i++){
                    headers.add(Constants.WHATSAPP_PROMO_FILE_HEADER + i);
                }
            }
        }

        for(int i = 1; i <= template.getBodyVarCount(); i++){
            headers.add(Constants.WHATSAPP_PROMO_FILE_BODY + i);
        }

        if(template.isButtonLink()){
            headers.add(Constants.WHATSAPP_PROMO_FILE_BUTTON);
        }

        return headers;
    }
}
