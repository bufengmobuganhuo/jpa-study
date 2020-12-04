package com.mengyu.jpa_study.chapter1.repository;

import com.mengyu.jpa_study.chapter1.entity.UserInfo;
import com.mengyu.jpa_study.chapter1.entity.UserInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuzhang
 * @date 2020/11/24 下午9:01
 * TODO
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, UserInfoId> {

}
