package com.avia.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.avia.config.StoConfiguration;
import com.avia.service.StoService;
import com.avia.util.OkHttpUtils;
import com.sto.link.util.SignatureUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class StoServiceImpl implements StoService {

    @Resource
    private StoConfiguration stoConfiguration;

    @Value("${sto.url}")
    private String url;

    @Override
    public Boolean verify(String mailNo) {
        // 内容
        Map<String, String> content = new HashMap<>();
        content.put("companyCode", stoConfiguration.getFromAppKey());
        content.put("maiNo", mailNo);
        String apiName = "SMS_CHECK_SEND";
        JSONObject request = request(content, apiName);
        return true;

    }

    @Override
    public Boolean sendSms(String categoryCode, String passage, String templateCode, String templateParam, String mailNo, String receiveNumber, String extend) {

        // 内容
        String apiName = "SMS_NORMAL_SEND";
        Map<String, String> content = new HashMap<>();
        content.put("companyCode", stoConfiguration.getFromCode());
        content.put("categoryCode", categoryCode);
        content.put("passage", passage);
        content.put("templateCode", templateCode);
        content.put("templateParam", templateParam);
        content.put("mailNo", mailNo);
        content.put("receiveNumber", receiveNumber);
        content.put("extend", extend);
        JSONObject request = request(content, apiName);


        return true;
    }

    @Override
    public Boolean sendSmsResultPush(String biZid, String status, String errCode, String errMessage, String receiver, String sendTime, String reptTime, String extend) {

        String apiName = "SMS_SEND_STATUS_NOTIFY";
        Map<String, String> content = new HashMap<>();
        content.put("bizId", biZid);
        content.put("status", status);
        content.put("errCode", errCode);
        content.put("errMessage", errMessage);
        content.put("receiver", receiver);
        content.put("sendTime", sendTime);
        content.put("reptTime", reptTime);
        content.put("extend", extend);
        JSONObject request = request(content, apiName);

        return true;
    }

    public JSONObject request(Map<String, String> content, String apiName) {

        // 参数拼接
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("api_name", apiName);
        paramMap.put("content", JSONObject.toJSONString(content));
        paramMap.put("from_appkey", stoConfiguration.getFromAppKey());
        paramMap.put("from_code", stoConfiguration.getFromCode());
        paramMap.put("to_appkey", stoConfiguration.getToAppkey());
        paramMap.put("to_code", stoConfiguration.getToCode());
        paramMap.put("data_digest", SignatureUtils.getSignature(JSONObject.toJSONString(content), stoConfiguration.getSecretKey()));
        String res = OkHttpUtils.builder().url(url).addParam(paramMap).post(false).async();
        log.info("响应为：{}", res);

        return JSONObject.parse(res);
    }

}
