package com.ennea.enneaservices.model.Dto;

import lombok.Data;

@Data
public class BroadcastTemplateDTO {

    private String id;

    private String templateName;

    private String template;

    private Boolean allowMediaAttachment;

}
