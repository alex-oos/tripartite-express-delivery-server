package com.avia.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex
 * @since 2024/4/16 下午2:52
 * <p></p>
 */
@Getter
// @Configuration
public class JtConfig {

    // api账号
    // @Value("${apiAccount}")
    public String apiAccount;

    // 平台密钥
    // @Value("${privateKey}")
    public String privateKey;

    // 协议客户账号
    // @Value("${customerCode}")
    public String customerCode;

    // 协议客户密码
    // @Value("${orderPassword}")
    public String orderPassword;

}
