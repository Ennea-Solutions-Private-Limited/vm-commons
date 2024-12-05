package com.ennea.enneaservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BroadcastTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;

    @ManyToOne
    private User user;

    private String parser;

    private String template;

    private String headerType;

    private boolean headerLink;

    private int headerVarCount;

    private int bodyVarCount;

    private boolean buttonLink;

    public static List<BroadcastTemplate> getDefaultDistributorTemplate(User user) {
        ArrayList<BroadcastTemplate> templates = new ArrayList<>();
        BroadcastTemplate divisionTemplate = new BroadcastTemplate();
        BroadcastTemplate productTemplate = new BroadcastTemplate();

        divisionTemplate.setTemplateName("valuemedi_distributor_division_promotion");
        productTemplate.setTemplateName("valuemedi_distributor_product_promotion");

        divisionTemplate.setUser(user);
        productTemplate.setUser(user);

        divisionTemplate.setParser("default_distributor_ad");
        productTemplate.setParser("default_distributor_ad");

        divisionTemplate.setHeaderType("image");
        productTemplate.setHeaderType("image");

        divisionTemplate.setHeaderLink(true);
        productTemplate.setHeaderLink(true);

        divisionTemplate.setHeaderVarCount(0);
        productTemplate.setHeaderVarCount(0);

        divisionTemplate.setBodyVarCount(3);
        productTemplate.setBodyVarCount(3);

        divisionTemplate.setButtonLink(false);
        productTemplate.setButtonLink(false);

        templates.add(divisionTemplate);
        templates.add(productTemplate);

        return templates;
    }

}
