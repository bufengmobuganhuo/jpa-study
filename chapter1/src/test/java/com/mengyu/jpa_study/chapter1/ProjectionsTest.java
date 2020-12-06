package com.mengyu.jpa_study.chapter1;

import com.mengyu.jpa_study.chapter1.dto.UserDto;
import com.mengyu.jpa_study.chapter1.dto.UserSimpleDto;
import com.mengyu.jpa_study.chapter1.repository.user.UserExtendRepository;
import com.mengyu.jpa_study.chapter1.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/11/24 下午7:31
 * 返回部分字段测试
 */
@SpringBootTest
public class ProjectionsTest {
    @Autowired
    private UserExtendRepository extendRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void test() {
        //extendRepository.save(UserExtend.builder().age(18).idCard("121").userId((long) 1).studentNumber("no1").build());
        List<UserDto> dtos = extendRepository.findByUserId(1L);
        System.err.println(Arrays.toString(dtos.toArray()));

        List<UserSimpleDto> dto = extendRepository.findByUserSimpleDtoId(1L);
        System.err.println(dto.get(0).getIdCard());
    }
}
