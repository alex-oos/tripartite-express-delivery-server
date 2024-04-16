package com.houji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <P></p>
 *
 * @author Alex
 * @since 2024/3/12 下午5:09
 * 线程池配置类
 */
@Configuration
public class ThreadPoolConfig {

    // 获取服务器的cpu个数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private static final int COUR_SIZE = CPU_COUNT * 2;

    private static final int MAX_COUR_SIZE = CPU_COUNT * 4;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(COUR_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(MAX_COUR_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(100);
        threadPoolTaskExecutor.setThreadNamePrefix("common-threadPool-");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setTaskDecorator(null);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}
