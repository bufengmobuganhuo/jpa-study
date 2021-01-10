package com.mengyu.jpa_study.chapter2.controller;

import com.mengyu.jpa_study.chapter2.entity.UserInfo;
import com.mengyu.jpa_study.chapter2.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user-info/{id}")
    public UserInfo getUserInfoInPath(@PathVariable("id") UserInfo userInfo) {
        return userInfo;
    }

    @GetMapping("/user-info")
    public UserInfo getUserInfoInReq(@RequestParam("id") UserInfo userInfo) {
        return userInfo;
    }

    @GetMapping("/users/sort")
    public Page<UserInfo> queryByPage(Pageable pageable, UserInfo userInfo) {
        return userInfoRepository.findAll(Example.of(userInfo), pageable);
    }

    @GetMapping("/users/page")
    public HttpEntity<List<UserInfo>> queryBySort(Sort sort) {
        return new HttpEntity<>(userInfoRepository.findAll(sort));
    }

    @PostMapping("/test/async/user")
    @Transactional
    public String testSaveUser(String name) {
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
}
