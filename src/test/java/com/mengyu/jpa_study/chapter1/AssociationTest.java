package com.mengyu.jpa_study.chapter1;

import com.mengyu.jpa_study.chapter1.entity.user.User;
import com.mengyu.jpa_study.chapter1.entity.user.UserInfo;
import com.mengyu.jpa_study.chapter1.repository.user.UserAddressRepository;
import com.mengyu.jpa_study.chapter1.repository.user.UserInfoRepository;
import com.mengyu.jpa_study.chapter1.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author yuzhang
 * @date 2020/12/5 下午12:32
 * TODO
 */
@SpringBootTest
public class AssociationTest {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Test
    void test_one_2_one() {
        User user = new User();
        user.setName("tom");
        user.setSex("male");
        user.setEmail("126@163.com");

        userRepository.save(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setAge(18);
        userInfo.setTelephone("18681820448");
        userInfo.setUser(user);

        UserInfo userInfo1 = userInfoRepository.save(userInfo);

        //Optional<User> user = userRepository.findById(14L);
        //System.err.println(user.orElse(null));

        Optional<UserInfo> res = userInfoRepository.findById(16L);
        res.ifPresent(info -> System.err.println(info + " user.id:" + info.getUser().getId()));
    }

    @Test
    @Rollback(false)
    @Transactional
    void test_many_2_one() {
        /*User user = new User();
        user.setName("tom");
        user.setSex("male");
        user.setEmail("126@163.com");

        UserAddress address = UserAddress.builder()
                .address("address")
                .user(user)
                .build();
        UserAddress address1 = UserAddress.builder()
                .address("address")
                .user(user)
                .build();
        userAddressRepository.saveAll(Arrays.asList(address, address1));*/

        User user = userRepository.getOne(30L);
        System.err.println(user.getName());
        System.err.println(user.getUserAddresses());
    }

    @Test
    void test_many_2_many(){

    }
}
