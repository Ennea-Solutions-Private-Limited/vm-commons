package com.ennea.enneaservices.notification.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEmailTemplate implements EmailTemplate {

    private transient String to;
    private transient String template;
    private transient List<String> ccList;
    private transient List<String> bccList;
    private HashMap<String, String> map = null;

    @Override
    public HashMap<String, String> getGenericMap() {
        return getMap();
    }

}
