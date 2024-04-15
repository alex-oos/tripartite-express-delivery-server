package com.volcano.listener;

import com.volcano.event.StoEvent;
import com.volcano.service.STOService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;

public class StoListener {


    @Resource
    private STOService stoService;

    @EventListener
    @Async
    public void onApplicationEvent(StoEvent event) {

        stoService.sendSmsResultPush();

    }

}
