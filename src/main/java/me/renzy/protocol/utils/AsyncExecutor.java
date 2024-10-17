package me.renzy.protocol.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class AsyncExecutor {

    private static final ExecutorService executor = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("protocol-async-executor-%d").build());

    public static void execute(@NonNull Runnable runnable) {
        executor.execute(runnable);
    }

    private AsyncExecutor() {}
}
