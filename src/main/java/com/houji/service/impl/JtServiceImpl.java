package com.houji.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.houji.service.JtService;
import com.yl.jms.sdk.JtExpressApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
 * @since 2024/4/16 下午2:51
 * <p></p>
 */
@Slf4j
@Service
public class JtServiceImpl implements JtService {

    @Resource
    private JtExpressApi JtExpressApi;

    @Value(value = "${jt.url}")
    private String url;

    @Override
    public Boolean verify(String mailNo) {

        Map<String, Object> pram = new HashMap<>();
        pram.put("waybillNo", mailNo);
        // 把接口需要的参数封装到map
        // Map<String, Object> map = new HashMap<>();
        // map.put("bizContent", JSONObject.toJSONString(pram));
        // val jsonString = JSONObject.toJSONString(map);
        // System.out.println(jsonString);
        // 调用执行器,调用接口方法
        JSONObject res = null;
        try {
            res = JtExpressApi.post(pram, url);
            log.info("reponse is {}", res.toJSONString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    @Override
    public Boolean sendSms(String type, String[] content, String waybillNo) {

        Map<String, Object> pram = new HashMap<>();
        pram.put("waybillNo", waybillNo);
        pram.put("type", type);
        pram.put("params", content);
        // 把接口需要的参数封装到map
        Map<String, Object> map = new HashMap<>();
        map.put("bizContent", JSONObject.toJSONString(pram));
        // 调用执行器,调用接口方法
        JSONObject res = null;
        try {
            res = JtExpressApi.post(map, url);
            log.info("response is {}", res.toJSONString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return null;

    }

}
