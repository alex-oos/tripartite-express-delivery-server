package com.avia.service;

/**
 * 申通服务
 */
public interface StoService {

    /**
     * 验证短信发送接口
     * @param mailNo
     * @return
     */
    Boolean verify(String mailNo);

    /**
     *  短信发送接口
     */
    Boolean sendSms();

    /**
     * 短信发送结果推送
     */
    Boolean sendSmsResultPush();
}
