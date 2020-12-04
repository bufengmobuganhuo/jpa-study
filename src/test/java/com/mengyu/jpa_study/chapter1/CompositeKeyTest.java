package com.mengyu.jpa_study.chapter1;

import com.mengyu.jpa_study.chapter1.entity.UserInfo;
import com.mengyu.jpa_study.chapter1.entity.UserInfoId;
import com.mengyu.jpa_study.chapter1.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author yuzhang
 * @date 2020/11/24 下午9:03
 * 联合主键测试
 */
@SpringBootTest
public class CompositeKeyTest {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    void testIdClass(){
        //userInfoRepository.save(UserInfo.builder().ages(1).name("jack").telephone("1234").build());
        Optional<UserInfo> userInfo =
                userInfoRepository.findById(UserInfoId.builder().name("jack").telephone("1234").build());
        System.err.println(userInfo.get());
    }
}
