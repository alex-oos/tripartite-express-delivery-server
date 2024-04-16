package com.avia.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.avia.config.StoConfiguration;
import com.avia.service.StoService;
import com.avia.util.BaseUtil;
import com.avia.util.OkHttpUtils;
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
    private String url ;

    @Override
    public Boolean verify(String mailNo) {
        // 内容
        Map<String, String> content = new HashMap<>();
        content.put("companyCode", stoConfiguration.getAppKey());
        content.put("maiNo", mailNo);
        String dataDigest = BaseUtil.calculateDigest(JSONObject.toJSONString(content), stoConfiguration.getSecretKey());

        // 参数拼接
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("api_name", "SMS_CHECK_SEND");
        paramMap.put("content", JSONObject.toJSONString(content));
        paramMap.put("from_appkey", stoConfiguration.getAppKey());
        paramMap.put("from_code", stoConfiguration.getAppKey());
        paramMap.put("to_appkey", stoConfiguration.getAppKey());
        paramMap.put("to_code", stoConfiguration.getAppKey());
        paramMap.put("data_digest", dataDigest);
        String res = OkHttpUtils.builder().url(url).addParam(paramMap).post(false).async();
        log.info("响应为：{}", res);

        JSONObject response = JSONObject.parse(res);
        return response.getBoolean("success");
    }

    @Override
    public Boolean sendSms() {

        return null;
    }

    @Override
    public Boolean sendSmsResultPush() {

        return null;
    }

}
