package com.example.springbatch;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Component
public class CustomRetryListener implements RetryListener {
    @Override
    public <T, E extends Throwable> boolean open(final RetryContext retryContext, final RetryCallback<T, E> retryCallback) {
        System.out.println("open");
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(final RetryContext retryContext, final RetryCallback<T, E> retryCallback, final Throwable throwable) {
        System.out.println("close");
    }

    @Override
    public <T, E extends Throwable> void onError(final RetryContext retryContext, final RetryCallback<T, E> retryCallback, final Throwable throwable) {
        System.out.println("Retry count : " + retryContext.getRetryCount());
    }
}
