package com.ennea.enneaservices.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum ParserEnum {

    BULK_ORDER("bulk_order",
               new String[]{"Supplier name", "Product", "Packing", "Quantity", "AutoMap Products", "AutoMap Suppliers"}),
    INVENTORY("inventory",
              new String[]{"Product name", "Packing", "Availability", "Product code", "MRP", "Sale rate", "Expiry date", "Deal", "Free", "Scheme", "Division"}),
    LEDGER("ledger",
           new String[]{"Party code", "Party name", "Alias", "Address", "Drug license", "Phone number", "Email", "Salesman code"}),

    DIST_DIVISION_PROMO("valuemedi_distributor_division_promotion", new String[]{"Phone number", "Division Name"}),
    DIST_PRODUCT_PROMO("valuemedi_distributor_product_promotion", new String[]{"Phone number", "Product Name"}),

    VALUEMEDI_FEATURE_VIDEO_DEMO("valuemedi_feature_video_demo", new String[]{"number", "body1", "body2"}),
    VALUEMEDI_PARTNERSHIP_BANNER_ANNOUNCEMENT("valuemedi_partnership_banner_announcement",
                                              new String[]{"number", "body1"}),
    VALUEMEDI_PARTNERSHIP_DOCUMENT_ANNOUNCEMENT("valuemedi_partnership_document_announcement",
                                                new String[]{"number", "body1"}),
    VALUEMEDI_PARTNERSHIP_VIDEO_ANNOUNCEMENT("valuemedi_partnership_video_announcement",
                                             new String[]{"number", "body1"}),
    VALUEMEDI_FEATURE_BANNER_ANNOUNCEMENT("valuemedi_feature_banner_announcement",
                                          new String[]{"number", "body1", "body2"}),
    VALUEMEDI_FEATURE_VIDEO_ANNOUNCEMENT("valuemedi_feature_video_announcement",
                                         new String[]{"number", "body1", "body2"}),
    VALUEMEDI_FEATURE_USER_GUIDE("valuemedi_feature_user_guide ", new String[]{"number", "body1", "body2"}),
    VALUEMEDI_NEW_DISTRIBUTOR_BANNER_ANNOUNCEMENT("valuemedi_new_distributor_banner_announcement",
                                                  new String[]{"number", "body1", "body2"}),
    VALUEMEDI_NEW_DISTRIBUTOR_VIDEO_ANNOUNCEMENT("valuemedi_new_distributor_video_announcement",
                                                 new String[]{"number", "body1", "body2"}),
    VALUEMEDI_EXISTING_RETAILERS_PROMPT("valuemedi_existing_retailers_prompt", new String[]{"number"}),
    VALUEMEDI_NEW_USER_VIDEO_DEMO("valuemedi_new_user_video_demo", new String[]{"number", "body1"}),
    VALUEMEDI_NEW_USER_VIDEO_PROMOTION("valuemedi_new_user_video_promotion", new String[]{"number", "body1", "button"}),
    VALUEMEDI_INACTIVE_USER_VIDEO_PROMOTION("valuemedi_inactive_user_video_promotion", new String[]{"number", "body1"}),
    VALUEMEDI_INACTIVE_USER_BANNER_PROMOTION("valuemedi_inactive_user_banner_promotion",
                                             new String[]{"number", "body1"}),
    VALUEMEDI_NEW_USER_BANNER_PROMOTION("valuemedi_new_user_banner_promotion",
                                        new String[]{"number", "body1", "button"}),
    VALUEMEDI_MINOR_FEATURE_ANNOUNCEMENT("valuemedi_minor_feature_announcement", new String[]{"number", "body1"}),
    VALUEMEDI_BNPL_INTEGRATION_NORMAL_PROMOTION("valuemedi_bnpl_integration_normal_promotion ",
                                                new String[]{"number", "body1", "body2"});

    public static final Map<String, String> defaultValuesMap;
    private static final Map<String, ParserEnum> map;

    static {
        defaultValuesMap = new HashMap<>();
        defaultValuesMap.put("AutoMap Products", "true");
        defaultValuesMap.put("AutoMap Suppliers", "false");
    }

    static {
        map = new HashMap<>();
        for(ParserEnum v : ParserEnum.values()){
            map.put(v.templateName, v);
        }
    }

    private final String templateName;
    private final String[] fields;

    ParserEnum(String templateName, String[] fields) {
        this.templateName = templateName;
        this.fields = fields;
    }

    public static ParserEnum findByTemplateName(String key) {
        return map.get(key);
    }

    public String getTemplateName() {
        return templateName;
    }

    public List<String> getFields() {
        return new ArrayList<>(Arrays.asList(fields));
    }

    public HashMap<String, String> getFieldsAsMap() {
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        for(String field : fields){
            if(defaultValuesMap.containsKey(field)){
                result.put(field, defaultValuesMap.get(field));
                continue;
            }
            result.put(field, "");
        }
        return result;
    }
}
