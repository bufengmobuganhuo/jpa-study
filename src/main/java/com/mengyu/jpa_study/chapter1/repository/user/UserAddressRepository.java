package com.mengyu.jpa_study.chapter1.repository.user;

import com.mengyu.jpa_study.chapter1.entity.user.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuzhang
 * @date 2020/12/5 下午4:17
 * TODO
 */
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
