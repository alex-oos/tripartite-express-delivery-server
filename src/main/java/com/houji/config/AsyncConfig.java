package com.houji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * <P></p>
 *
 * @author Alex
 * @since 2024/3/12 下午5:11
 * 异步线程池配置
 */
// @Configuration
// @EnableAsync
public class AsyncConfig {


    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private static final int CORE_POOL_SIZE = CPU_COUNT * 2;

    private static final int MAX_POOL_SIZE = CPU_COUNT * 4;


    @Bean
    public TaskExecutor taskExecutor() {
        // 采用spring 中的线程池配置，优点是比自己配置线程池更简单
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(CORE_POOL_SIZE);
        // 设置最大线程数
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        // 设置队列
        executor.setQueueCapacity(100);
        // 设置线程池名称
        executor.setThreadNamePrefix("asyncThreadPool-");
        // 线程之间传递对象
        executor.setTaskDecorator(null);
        executor.initialize();
        return executor;
    }

}
