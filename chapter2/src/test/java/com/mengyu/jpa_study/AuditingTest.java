package com.mengyu.jpa_study;

import com.mengyu.jpa_study.chapter2.entity.User;
import com.mengyu.jpa_study.chapter2.enums.SexEnum;
import com.mengyu.jpa_study.chapter2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yuzhang
 * @date 2020/12/9 下午7:08
 * 审计功能测试
 */
@SpringBootTest
public class AuditingTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void test_auditing() {
        User user = User.builder()
                .name("jack")
                .email("163.com")
                .sex(SexEnum.BOY)
                .age(15)
                .build();
        userRepository.save(user);
    }
}
