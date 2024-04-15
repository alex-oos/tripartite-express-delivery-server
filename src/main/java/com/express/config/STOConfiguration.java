package com.express.config;


import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Configuration
@ConfigurationProperties(prefix = "express.sto")
public class STOConfiguration {

    private String appKey;

    private String secretKey;

    private String code;

    private String url;

    private String toAppkey;

}
