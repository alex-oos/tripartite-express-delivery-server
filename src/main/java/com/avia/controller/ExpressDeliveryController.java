package com.avia.controller;

import com.avia.service.BaseService;
import com.avia.service.JtService;

import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ExpressDeliveryController {

    @Resource
    private BaseService baseService;

    @Resource
    private JtService jtService;


    @GetMapping(value = "/mailNo")
    public String sendSms(@RequestParam String mailNo) {

        return baseService.sendSms(mailNo);
    }

    @GetMapping(value = "/jt")
    public String jt(@RequestParam String maiNo){
        Boolean verify = jtService.verify(maiNo);
        System.out.println(verify);
        return null;
    }



}
