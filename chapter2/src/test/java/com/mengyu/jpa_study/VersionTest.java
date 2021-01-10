package com.mengyu.jpa_study;

import com.mengyu.jpa_study.chapter2.entity.User;
import com.mengyu.jpa_study.chapter2.entity.UserInfo;
import com.mengyu.jpa_study.chapter2.repository.UserInfoRepository;
import com.mengyu.jpa_study.chapter2.service.UserInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author yuzhang
 * @date 2020/12/11 下午4:32
 * TODO
 */
@SpringBootTest
public class VersionTest {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    void test_version() {
        UserInfo userInfo = UserInfo.builder()
                .age(15)
                .telephone("12345")
                .build();
        userInfo = userInfoRepository.save(userInfo);
        // 插入时，会自动为version字段赋值为0
        Assertions.assertEquals(0, userInfo.getVersion());
        userInfoService.calculate(userInfo.getId());
        // 更新后，version字段的值会被+1
        UserInfo userInfo1 = userInfoRepository.findById(userInfo.getId()).get();
        Assertions.assertEquals(1, userInfo1.getVersion());
    }

    @Test
    @Transactional
    void test_version_exception() {
        UserInfo userInfo = UserInfo.builder()
                .age(15)
                .telephone("12345")
                .build();
        userInfoRepository.saveAndFlush(userInfo);
        // 模拟多线程执行两次更新操作
        new Thread(() -> {
            userInfoService.calculate(34L);
        }).start();
        try {
            TimeUnit.MICROSECONDS.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 执行第二次更新操作，可能发生乐观锁异常
        Exception exception = Assertions.assertThrows(ObjectOptimisticLockingFailureException.class,
                () -> userInfoService.calculate(34L));
        System.err.println(exception);
    }

    @Test
    void test_retry(){
        // 先开启一个更新操作
        new Thread(()->{
            userInfoService.calculate(36L);
        }).start();
        try {
            TimeUnit.MICROSECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再执行一次更新操作，此时可能会发生乐观锁冲突，从而导致重试
        UserInfo userInfo = userInfoService.calculate(36L);
        // 由于两次都更新成功了，version=2
        Assertions.assertEquals(userInfo.getVersion(),2);
    }
}
