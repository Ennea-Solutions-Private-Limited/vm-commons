package com.ennea.enneaservices.model;

import com.ennea.enneaservices.enums.BannerEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class RoleBanner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Role role;

    @Enumerated(EnumType.STRING)
    private BannerEnum bannerType;

    private String redirectLink;

    private String product;

    private String direction;

    private String mobileBannerLink;

    private String webBannerLink;

    public String getType() {
        return bannerType.getType();
    }
}
