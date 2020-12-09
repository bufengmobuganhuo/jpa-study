package com.mengyu.jpa_study;

import com.mengyu.jpa_study.chapter2.entity.User;
import com.mengyu.jpa_study.chapter2.entity.UserAddress;
import com.mengyu.jpa_study.chapter2.enums.SexEnum;
import com.mengyu.jpa_study.chapter2.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

/**
 * @author yuzhang
 * @date 2020/12/8 下午8:12
 * TODO
 */
@SpringBootTest
public class RepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void test_customizedUserRepository(){
        User user = userRepository.findById(1L).get();
        user.setName("jack");
        user.setAge(10);
        userRepository.actualUpdateName(user);
        User user1 = userRepository.findById(1L).get();
        Assertions.assertEquals(user.getAge(),user1.getAge());
    }

    @Test
    void test_override(){
        User user = User.builder()
                .age(15)
                .email("126.fadf")
                .sex(SexEnum.BOY)
                .name("tom")
                .address(Lists.newArrayList(UserAddress.builder().address("beijing").build()))
                .build();
        userRepository.save(user);
    }
}
