package com.ennea.enneaservices.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "mail")
@Configuration
@Getter
@Setter
public class MailProperties {

    private String fromAddress;

    private String toAddress;

    private String configSet;
}
