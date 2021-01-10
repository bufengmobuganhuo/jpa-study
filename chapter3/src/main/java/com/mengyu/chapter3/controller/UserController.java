package com.mengyu.chapter3.controller;

import com.mengyu.chapter3.entity.UserInfo;
import com.mengyu.chapter3.helper.TransactionHelper;
import com.mengyu.chapter3.repository.UserInfoRepository;
import com.mengyu.chapter3.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhang
 * @date 2020/12/14 下午5:00
 * TODO
 */
@RestController
@AllArgsConstructor
public class UserController {
    private final UserInfoRepository userInfoRepository;

    private final Executor executor;

    private final UserInfoService userInfoService;

    private final TransactionHelper transactionHelper;

    @GetMapping("/test/async/exp")
    @Transactional
    public String testSaveUserExp() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            UserInfo userInfo = userInfoRepository.findById(1L).get();
            try {
                // 模拟业务逻辑处理耗时
                TimeUnit.MICROSECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 第一次修改保存
            userInfo.setAge(new Random().nextInt());
            userInfoRepository.save(userInfo);

            try {
                // 模拟业务逻辑处理耗时
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 第二次修改保存
            userInfo.setAge(new Random().nextInt());
            userInfoRepository.save(userInfo);
        }, executor).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
        cf.isDone();
        return "Success";
    }

    @GetMapping("/test/async/no-exp/method1")
    @Transactional
    public String testSaveUserNoExpMethod1() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            userInfoService.businessUserInfoMethod();
        }, executor).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
        cf.isDone();
        return "Success";
    }

    @GetMapping("/test/async/no-exp/method2")
    @Transactional
    public String testSaveUserNoExpMethod2() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            transactionHelper.transactional((param) -> {
                UserInfo userInfo = userInfoRepository.findById(1L).get();
                try {
                    // 模拟业务操作耗时
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userInfo.setAge(new Random().nextInt());
                userInfoRepository.save(userInfo);

                try {
                    // 模拟业务操作耗时
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userInfo.setAge(new Random().nextInt());
                userInfoRepository.save(userInfo);
            },null);
        }, executor).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
        cf.isDone();
        return "Success";
    }
}
