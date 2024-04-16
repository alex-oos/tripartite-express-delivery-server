package com.houji.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.houji.config.ZtoConfiguration;
import com.houji.service.ZtoService;
import com.zto.zop.ZopClient;
import com.zto.zop.ZopPublicRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.zto.zop.EncryptionType.MD5;


@Slf4j
@Service
public class ZtoServiceImpl implements ZtoService {

    @Resource
    private ZtoConfiguration ztoConfiguration;

    @Value("${zto.url}")
    private String url;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public Boolean verify(String mailNo) {


        Map<Object, Object> params = new HashMap<>();
        params.put("billCode", mailNo);
        return requestZto(params);
    }

    @Override
    public Boolean isCanSendSms(String companyCode, String billCode) {


        Map<Object, Object> params = new HashMap<>();
        params.put("companyCode", companyCode);
        params.put("billCode", billCode);
        return requestZto(params);
    }

    @Override
    public Boolean sendSms(String billCode, String mobile, String bizld, String content, String smsSign) {

        Map<Object, Object> params = new HashMap<>();
        params.put("billCode", billCode);
        params.put("mobile", mobile);
        params.put("bizId", bizld);
        params.put("content", content);
        params.put("smsSign", smsSign);
        return requestZto(params);
    }


    public Boolean requestZto(Map<Object, Object> params) {

        ZopClient client = new ZopClient(ztoConfiguration.getAppKey(), ztoConfiguration.getSecretKey());
        ZopPublicRequest request = new ZopPublicRequest();
        request.setBody(JSONObject.toJSONString(params));
        request.setUrl(url);
        request.setBase64(true);
        request.setEncryptionType(MD5);
        request.setTimestamp(null);


        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            try {
                String response = client.execute(request);
                log.info("response is {}", response);
                JSONObject jsonObject = JSONObject.parseObject(response);
                return jsonObject.getBoolean("status");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, threadPoolTaskExecutor);

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {

        ZtoServiceImpl ztoService = new ZtoServiceImpl();
        ztoService.verify("73100039415086");
    }

}
