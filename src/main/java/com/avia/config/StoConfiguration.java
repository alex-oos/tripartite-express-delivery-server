package com.avia.config;


import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class StoConfiguration {


    private String appKey = "CAKakHJIOTyuGko";

    private String secretKey = "7RrimcFVGUk2mK5uuCVZYXWVjbt4SpCv";

    private String code = "CAKakHJIOTyuGko";

    private String toAppkey = "market_message";

}
