package com.volcano.listener;

import com.volcano.event.StoEvent;
import com.volcano.service.STOService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;

public class StoListener {


    @Resource
    private STOService stoService;
    // 为了更快使用监听器，后期也可以不用，监听器需要增加一个发布者，发布者，发布一个内容，然后监听器监听到内容，开始发送消息，多用于短信服务，或消息分发
    @EventListener
    @Async
    public void onApplicationEvent(StoEvent event) {

        stoService.sendSmsResultPush();

    }

}
