package com.houji.service;

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
    Boolean sendSms(String categoryCode, String passage, String templateCode, String templateParam, String mailNo, String receiveNumber, String extend);

    /**
     * 短信发送结果推送
     */
    Boolean sendSmsResultPush(String biZid, String status, String errCode, String errMessage, String receiver, String sendTime, String reptTime, String extend);
}
