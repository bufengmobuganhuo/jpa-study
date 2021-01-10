package com.mengyu.chapter3.helper;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

/**
 * @author yuzhang
 * @date 2021/1/5 下午9:04
 * TODO
 */
@Component
public class TransactionHelper {
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, backoff = @Backoff(multiplier = 1.5, random = true))
    public void transactional(Consumer consumer, Object o){
        consumer.accept(o);
    }
}
