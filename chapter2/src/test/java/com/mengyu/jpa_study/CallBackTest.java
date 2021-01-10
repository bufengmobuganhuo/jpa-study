package com.mengyu.jpa_study;

import com.mengyu.jpa_study.chapter2.entity.User;
import com.mengyu.jpa_study.chapter2.entity.UserAddress;
import com.mengyu.jpa_study.chapter2.enums.SexEnum;
import com.mengyu.jpa_study.chapter2.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yuzhang
 * @date 2020/12/10 下午5:00
 * @Entity 的回调函数测试
 */
@SpringBootTest
public class CallBackTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void test_persist(){
        User user = User.builder()
                .age(15)
                .sex(SexEnum.GIRL)
                .email("125email")
                .address(Lists.newArrayList(UserAddress.builder().address("hangzhou").build()))
                .name("lucy")
                .build();
        // 触发@Persist相关的回调方法
        user = userRepository.save(user);
    }

    @Test
    void test_update_and_remove(){
        User user = userRepository.getOne(2L);
        user.setName("xxx");
        // 触发@Update相关操作
        userRepository.save(user);
        // 触发@Remove相关操作
        userRepository.delete(user);
    }
}
