package com.avia.service;

/**
 * @author Alex
 * @since 2024/4/16 下午2:51
 * <p></p>
 */
public interface JtService {

    /**
     * 验证短信发送接口
     *
     * @param mailNo
     * @return
     */
    Boolean verify(String mailNo);

    /**
     * 短信发送接口
     */
    Boolean sendSms(String type, String[] content, String waybillNo);



}
