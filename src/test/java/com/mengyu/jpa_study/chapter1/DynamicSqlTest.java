package com.mengyu.jpa_study.chapter1;

import com.mengyu.jpa_study.chapter1.dto.UserOnlyNameAndEmail;
import com.mengyu.jpa_study.chapter1.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/11/24 下午8:02
 * 动态SQL测试
 */
@SpringBootTest
public class DynamicSqlTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void test(){
        List<UserOnlyNameAndEmail> users = userRepository.findByUser("jack",null);
        System.err.println(Arrays.toString(users.toArray()));

        users = userRepository.findByUser("jack","123@126.com");
        System.err.println(Arrays.toString(users.toArray()));
    }
}
