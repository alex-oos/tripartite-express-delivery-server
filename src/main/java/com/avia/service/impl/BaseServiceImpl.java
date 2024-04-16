package com.avia.service.impl;

import com.avia.service.BaseService;
import com.avia.service.JtService;
import com.avia.service.StoService;
import com.avia.service.ZtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class BaseServiceImpl implements BaseService {

    @Resource
    private StoService stoService;

    @Resource
    private ZtoService ztoService;

    @Resource
    private JtService jtService;

    @Resource
    private ThreadPoolTaskExecutor threadPoolExecutor;

    @Override
    public String sendSms(String mailNo) {
        // 1，先验证是哪一家快递公司
        String companyName = this.verifyExpressCompany(mailNo);
        // 2.发送快递
        // 3.拿到结果
        switch (companyName) {
            case "sto":
                stoService.sendSms();
                stoService.sendSmsResultPush();
                return "aa";
            case "zto":
                ztoService.isCanSendSms("1", "1");
                ztoService.sendSms("1", "1", "1", "1", "1");
                return "zto";
            case "jt":
                jtService.sendSms("type", new String[]{"1", "2"}, "11");
                return "jt";
            default:
                return "数据为空";
        }


    }

    @Override
    public String verifyExpressCompany(String mailNo) {

        List<CompletableFuture<String>> list = new ArrayList<>();
        // 申通快递
        CompletableFuture<String> stoFuture = CompletableFuture.supplyAsync(() -> {
            Boolean verify = stoService.verify(mailNo);
            if (verify) {
                return "sto";
            }
            return null;
        }, threadPoolExecutor);
        // 中通快递
        CompletableFuture<String> ztoFuture = CompletableFuture.supplyAsync(() -> {
            Boolean verify = ztoService.verify(mailNo);
            if (verify) {
                return "zto";
            }
            return null;
        }, threadPoolExecutor);

        CompletableFuture<String> jtFuture = CompletableFuture.supplyAsync(() -> {
            Boolean verify = jtService.verify(mailNo);
            if (verify) {
                return "jt";
            }
            return null;
        }, threadPoolExecutor);


        list.add(stoFuture);
        list.add(ztoFuture);
        list.add(jtFuture);
        // 获取所有结果
        for (CompletableFuture<String> booleanCompletableFuture : list) {
            try {
                String companyName = booleanCompletableFuture.get();
                if (Objects.nonNull(companyName)) {
                    return companyName;
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }


}
