package com.mengyu.jpa_study.chapter2.repository;

import com.mengyu.jpa_study.chapter2.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuzhang
 * @date 2020/12/11 下午4:29
 * TODO
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
}
