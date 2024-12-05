package com.ennea.enneaservices.model.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleBannerDTO implements Serializable {

    private RoleDTO role;

    private String bannerType;

    private String redirectLink;

    private String product;

    private String direction;

    private String mobileBannerLink;

    private String webBannerLink;
}
