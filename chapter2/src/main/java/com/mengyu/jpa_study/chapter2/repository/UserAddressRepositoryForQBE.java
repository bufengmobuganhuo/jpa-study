package com.mengyu.jpa_study.chapter2.repository;

import com.mengyu.jpa_study.chapter2.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuzhang
 * @date 2020/12/6 下午12:06
 * TODO
 */
public interface UserAddressRepositoryForQBE extends JpaRepository<UserAddress,Long> {
}
