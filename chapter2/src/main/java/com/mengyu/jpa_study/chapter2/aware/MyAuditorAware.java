package com.mengyu.jpa_study.chapter2.aware;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.Random;

/**
 * @author yuzhang
 * @date 2020/12/9 下午7:04
 * TODO
 */
public class MyAuditorAware implements AuditorAware<Integer> {
    /**
     * 返回当前用户的ID
     * @return
     */
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Random random = new Random();
        return Optional.of(random.nextInt());
    }
}
