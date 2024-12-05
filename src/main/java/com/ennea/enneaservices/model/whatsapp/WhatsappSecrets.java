package com.ennea.enneaservices.model.whatsapp;

import com.ennea.enneaservices.model.Dto.secretsmanager.Secrets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WhatsappSecrets extends Secrets {

    private String authcode;
    private String url;
    private String metaversion;
    private String phoneid;
    private String urlsuffix;
    private String verifytoken;

    public String getEndpointURL() {
        return this.getUrl() + "/" + this.getMetaversion() + "/" + this.getPhoneid() + "/" + this.getUrlsuffix();
    }

}
