package com.mengyu.jpa_study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mengyu.jpa_study.chapter2.entity.User;
import com.mengyu.jpa_study.chapter2.entity.UserAddress;
import com.mengyu.jpa_study.chapter2.enums.SexEnum;
import com.mengyu.jpa_study.chapter2.repository.UserAddressRepositoryForQBE;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;

/**
 * @author yuzhang
 * @date 2020/12/6 下午12:07
 * TODO
 */
@SpringBootTest
public class QBETest {
    @Autowired
    private UserAddressRepositoryForQBE userAddressRepositoryForQBE;

    private Date now = new Date();

    @BeforeTestClass
    @Rollback(false)
    @Transactional
    public void init(){
        User user = User.builder()
                .name("jack")
                .email("126.163.com")
                .sex(SexEnum.BOY)
                .age(20)
                .createDate(Instant.now())
                .updateDate(now)
                .build();
        userAddressRepositoryForQBE.saveAll(Lists.newArrayList(UserAddress.builder().user(user).addres("shanghai").build(),
                UserAddress.builder().user(user).addres("beijing").build()));
    }

    @Test
    void test_QBE_From_User_Address() throws JsonProcessingException {
        User request = User.builder()
                .name("jack")
                .age(20)
                .email("126.163.com")
                .build();
        UserAddress address = UserAddress.builder().addres("shang").user(request).build();
        ObjectMapper objectMapper = new ObjectMapper();
        System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address));

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("user.email",ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("address",ExampleMatcher.GenericPropertyMatchers.startsWith());
        Page<UserAddress> page = userAddressRepositoryForQBE.findAll(Example.of(address,exampleMatcher), PageRequest.of(0,2));
        System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(page));
    }
}
