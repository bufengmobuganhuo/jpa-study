package com.mengyu.jpa_study.chapter1;

import com.mengyu.jpa_study.chapter1.entity.user.User;
import com.mengyu.jpa_study.chapter1.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author yuzhang
 * @date 2020/11/22 上午10:51
 * 测试 @Query的用法
 */
@SpringBootTest
public class QueryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testQueryAnnotation(){
        List<User> user = userRepository.findByQuery("jack");
        System.out.println(user);

        List<User> users = userRepository.findByEmailAddress("123@126.com");
        System.err.println(users);

        users = userRepository.findByNameEndsWith("k");
        System.err.println(users);

        users = userRepository.findByEmail("123@126.com");
        System.err.println(users);

        users = userRepository.findByEmail("123@126.com","name");
        System.err.println(users);

        users = userRepository.findByAndSort("k", Sort.by(Sort.Order.by("name")));
        System.err.println(users);
    }

}
