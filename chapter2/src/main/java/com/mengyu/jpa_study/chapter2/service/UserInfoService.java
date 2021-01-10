package com.mengyu.jpa_study.chapter2.service;

import com.mengyu.jpa_study.chapter2.entity.UserInfo;
import com.mengyu.jpa_study.chapter2.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author yuzhang
 * @date 2020/12/11 下午4:30
 * TODO
 */
@Service
@AllArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Retryable
    @Transactional
    public UserInfo calculate(Long id){
        UserInfo userInfo = userInfoRepository.findById(id).get();
        try {
            TimeUnit.SECONDS.sleep( 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userInfo.setAge(userInfo.getAge()+1);
        return userInfoRepository.saveAndFlush(userInfo);
    }
}
