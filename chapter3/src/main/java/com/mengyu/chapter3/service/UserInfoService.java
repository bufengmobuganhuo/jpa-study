package com.mengyu.chapter3.service;

import com.mengyu.chapter3.entity.UserInfo;
import com.mengyu.chapter3.repository.UserInfoRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhang
 * @date 2021/1/5 下午8:45
 * TODO
 */
@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    // 加上事务，这样可以做到原子性，解决事务加到异常方法之外没有任何作用的问题
    @Transactional
    //加上重试机制，这样当我们发生乐观锁异常的时候，重新尝试下面的逻辑，减少请求的失败次数
    @Retryable(value = ObjectOptimisticLockingFailureException.class,backoff = @Backoff(multiplier = 1.5,random = true))
    public void businessUserInfoMethod(){
        UserInfo userInfo = userInfoRepository.findById(1L).get();
        try {
            TimeUnit.MICROSECONDS.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userInfo.setAge(new Random().nextInt());
        userInfoRepository.save(userInfo);

        try {
            TimeUnit.MICROSECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userInfo.setAge(new Random().nextInt());
        userInfoRepository.save(userInfo);
    }
}
