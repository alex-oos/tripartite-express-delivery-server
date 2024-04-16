package com.avia.service.impl;

import com.avia.service.BaseService;
import com.avia.service.StoService;
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
    private ThreadPoolTaskExecutor threadPoolExecutor;

    @Override
    public String sendSms(String mailNo) {
        // 1，先验证是哪一家快递公司
        String companyName = this.verifyExpressCompany(mailNo);
        // 2.发送快递
        // 3.拿到结果
        switch (companyName) {
            case "STO":
                stoService.sendSms();
                stoService.sendSmsResultPush();
                return "aa";
            case "demo":
                return "demo";
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
                return "STO";
            }
            return null;
        }, threadPoolExecutor);
        // 其他快递公司


        //


        list.add(stoFuture);
        // 获取所有结果
        for (CompletableFuture<String> booleanCompletableFuture : list) {
            try {
                String companyName = booleanCompletableFuture.get();
                if (Objects.nonNull(companyName)) {
                    return companyName;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }

    public CompletableFuture<Boolean> sendSmsAsync(String mailNo) {

        return CompletableFuture.supplyAsync(() -> {
            return stoService.verify(mailNo);
        }, threadPoolExecutor);

    }

}
