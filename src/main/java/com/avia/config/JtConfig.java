package com.avia.config;

import com.yl.jms.sdk.JtExpressApi;
import com.yl.jms.sdk.auth.ClientConfiguration;
import com.yl.jms.sdk.client.JtExpressApiOperator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex
 * @since 2024/4/16 下午2:52
 * <p>极兔配置类</p>
 */
@Getter
@Configuration
public class JtConfig {

    // api账号
    @Value("178337126125932605")
    public String apiAccount;

    // 平台密钥
    @Value("0258d71b55fc45e3ad7a7f38bf4b201a")
    private String privateKey;

    // 协议客户账号
    @Value("J0086474299")
    private String customerCode;

    // 协议客户密码
    @Value("6A272C3DD1F3CD2F92BF567C37040910")
    private String pwd;

    @Bean
    public JtExpressApi jtExpressApi() {

        ClientConfiguration clientConfiguration = new ClientConfiguration(apiAccount, privateKey);
        // 当调用postByCustom方法时需要customerCode和orderPassword
        clientConfiguration.setCustomerCode(customerCode);
        clientConfiguration.setCustomerPwd(pwd);
        JtExpressApi jtExpressApi = new JtExpressApiOperator(clientConfiguration);
        return jtExpressApi;
    }

}
