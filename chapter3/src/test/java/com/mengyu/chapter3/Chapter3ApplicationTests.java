package com.mengyu.chapter3;

import com.mengyu.chapter3.entity.UserInfo;
import com.mengyu.chapter3.repository.AddressRepository;
import com.mengyu.chapter3.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Chapter3ApplicationTests {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testEntityGraph(){
        List<UserInfo> userInfos = userInfoRepository.findAll();
    }
}
