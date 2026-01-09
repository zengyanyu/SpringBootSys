package com.zengyanyu.system.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 默认线程工厂
 */
@Slf4j
public class DefaultThreadFactory implements ThreadFactory {

    private final String prefix;
    private final AtomicInteger number = new AtomicInteger(1);

    public DefaultThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    public DefaultThreadFactory() {
        this.prefix = "default-thread";
    }

    @Override
    public Thread newThread(@NonNull Runnable runnable) {
        Thread thread = new Thread(runnable, String.join("-", prefix, String.valueOf(number.getAndIncrement())));
        thread.setUncaughtExceptionHandler((t, e) -> log.error("Thread {} uncaught exception", t.getName(), e));
        return thread;
    }
}
