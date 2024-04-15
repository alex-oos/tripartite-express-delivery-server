package com.volcano.controller;

import com.volcano.service.BaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ExpressDeliveryController {

    @Resource
    private BaseService baseService;


    @GetMapping(value = "/mailNo")
    public String sendSms(@RequestParam String mailNo) {

        return baseService.sendSms(mailNo);
    }


}
