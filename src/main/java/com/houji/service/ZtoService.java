package com.houji.service;

public interface ZtoService {

    Boolean  verify(String trackingNumber);

    /**
     * 运单是否可代发短信校验
     * @param companyCode
     * @param type
     * @return
     */
    Boolean  isCanSendSms(String companyCode, String billCode);

    /**
     * 发送短信
     * @param billCode
     * @param mobile
     * @param bizld
     * @param content
     * @param smsSign
     * @return
     */
    Boolean sendSms(String billCode,String mobile,String bizld,String content,String smsSign);



}
