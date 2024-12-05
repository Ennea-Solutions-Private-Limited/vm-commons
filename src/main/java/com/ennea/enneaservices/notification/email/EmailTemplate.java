package com.ennea.enneaservices.notification.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public interface EmailTemplate {

    ObjectMapper mapper = new ObjectMapper();

    HashMap<String, String> getGenericMap();

    default String generateValueMap() {
        HashMap<String, String> genericMap = getGenericMap();
        try {
            if(genericMap != null && !genericMap.isEmpty()){
                return mapper.writeValueAsString(genericMap);
            } else{
                return mapper.writeValueAsString(this);
            }

        } catch (JsonProcessingException e) {
            return null;
        }
    }

}
