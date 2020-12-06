package com.mengyu.jpa_study.chapter1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mengyu.jpa_study.chapter1.dto.UserOnlyNameAndEmail;
import com.mengyu.jpa_study.chapter1.entity.user.User;
import com.mengyu.jpa_study.chapter1.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author yuzhang
 * @date 2020/11/24 下午8:02
 * JPA的不同返回值测试
 */
@SpringBootTest
class JpaReturnTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    /**
     * 测试Streamable的返回值
     */
    @Test
    void testStreamable(){
        /*userRepository.save(User.builder().name("jack").email("123@163.com").build());
        // 返回一个Streamable，后面还可以接集合的操作
        Streamable<User> users = userRepository.findAll(PageRequest.of(0,10))
                .and(User.builder().name("jack222").build());
        users.forEach(user -> {
            System.err.println(user.toString());
        });*/
    }

    @Test
    @Transactional
    void testStreamAndSlice() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Pageable request = PageRequest.of(0,3);

        System.err.println("------stream--------");
        Stream<User> userStream = userRepository.findAllByCustomQueryAndStream(request);
        userStream.forEach(System.out::println);

        System.err.println("--------page-----");
        Page<User> userPage = userRepository.findAll(request);
        System.out.println(objectMapper.writeValueAsString(userPage));

        System.err.println("-----slice-----");
        Slice<User> userSlice = userRepository.findAllByCustomQueryAndSlice(request);
        System.out.println(objectMapper.writeValueAsString(userSlice));

        System.err.println("-----list-----");
        List<User> userList = userRepository.findAll();
        System.out.println(objectMapper.writeValueAsString(userList));
    }

    @Test
    void testProjections(){
        List<UserOnlyNameAndEmail> user = userRepository.findByName("jack");
        user.forEach(item -> System.err.println(item.getName()+"  "+item.getEmail()));
    }
}
