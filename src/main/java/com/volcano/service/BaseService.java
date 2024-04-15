package com.volcano.service;

public interface BaseService {


    String sendSms(String mailNo);

    /**
     * 验证是哪一家快递公司
     *
     * @param mailNo
     */
    String verifyExpressCompany(String mailNo);

}
